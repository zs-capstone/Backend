import pandas as pd
import numpy as np
import numpy as np
from numpy import dot
from numpy.linalg import norm
from sklearn.metrics.pairwise import cosine_similarity
import time

class ItemCFtrain:
    df = pd.DataFrame()
    target_person = ""
    resultdf = pd.DataFrame()

    def __init__(self,target_person,day,n):
        self.df = pd.read_csv("./output.csv",quotechar="|")
        self.target_person = target_person
        self.day = day
        self.n = n

        self.total = day*n
        self.preprocess()
        return

    ## user, place 각각 20개 이상으로 전처리
    def preprocess(self):
        temp = self.df.groupby(['username']).filter(lambda x:len(x) >= 20).reset_index(drop=True)
        temp2 = temp.groupby(['place_id']).filter(lambda x: len(x) >= 20).reset_index(drop=True)
        self.df = temp2.copy(deep=True)

    def unique_items(self):
        unique_items_list = []
        for person in self.df.keys():
            for items in self.df[person]:
                unique_items_list.append(items)
        s=set(unique_items_list)
        unique_items_list=list(s)
        return unique_items_list
    
    # def adjusted_cosine_similarity(item1,item2,umean):
    #     item1 = np.array(item1)
    #     item2 = np.array(item2)

    #     item1 = item1-umean
    #     item2 = item2-umean

    #     s1 = math.sqrt(sum(item1*item1))
    #     s2 = math.sqrt(sum(item2*item2))

    #     if np.isnan(s1) or np.isnan(s2) or s1 == 0.0 or s2 == 0.0:
    #         return 0.0

    #     return sum((item1*item2)/(s1*s2))

    def item_similarity(self,item1,item2):
        both_rated = {}
        for person in self.df.keys():
            if self.df[person][item1]!=0.0 and self.df[person][item2]!=0.0:
                both_rated[person] = [self.df[person][item1],self.df[person][item2]]

        #print(both_rated)
        number_of_ratings = len(both_rated)
        if number_of_ratings == 0:
            return 0

        item1_ratings = [[self.df[k][item1] for k,v in both_rated.items() if item1 in self.df[k] and item2 in self.df[k]]]
        item2_ratings = [[self.df[k][item2] for k, v in both_rated.items() if item1 in self.df[k] and item2 in self.df[k]]]
        cs = cosine_similarity(item1_ratings,item2_ratings)
        return cs[0][0]


    # def adj_item_similarity(item1,item2,tu,dataset):
    #     both_rated = {}
    #     for person in dataset.keys():
    #         if dataset[person][item1]!=0.0 and dataset[person][item2]!=0.0:
    #             both_rated[person] = [dataset[person][item1],dataset[person][item2]]

    #     umean = 0.0
    #     cnt = 0
    #     for p in dataset[tu]:
    #         if dataset[tu][p] == 0.0:
    #             continue
    #         cnt+=1
    #         umean+=dataset[tu][p]

    #     umean = umean/cnt

    #     #print(both_rated)
    #     number_of_ratings = len(both_rated)
    #     if number_of_ratings == 0:
    #         return 0

    #     item1_ratings = [[dataset[k][item1] for k,v in both_rated.items() if item1 in dataset[k] and item2 in dataset[k]]]
    #     item2_ratings = [[dataset[k][item2] for k, v in both_rated.items() if item1 in dataset[k] and item2 in dataset[k]]]
    #     return adjusted_cosine_similarity(*item1_ratings,*item2_ratings,umean)

    def most_similar_items(self,target_item):
        un_lst=self.unique_items()
        scores = [(self.item_similarity(target_item,other_item),target_item+" --> "+other_item) for other_item in un_lst if other_item!=target_item]
        scores.sort(reverse=True)
        return scores

    def target_places_to_users(self,target_person):
        target_person_movie_lst = []
        unique_list = self.unique_items()

        for places,ratings in self.df[target_person].items():
            if ratings > 0.0:
                target_person_movie_lst.append(places)

        s=set(unique_list)
        recommended_places=list(s.difference(target_person_movie_lst))
        a = len(recommended_places)
        if a == 0:
            return 0
        return recommended_places,target_person_movie_lst

    # def adj_most_similar_items(target_item,target_user,dataset):
    #     un_lst=unique_items(dataset)
    #     scores = [(adj_item_similarity(target_item,other_item,target_user,dataset),target_item+" --> "+other_item) for other_item in un_lst if other_item!=target_item]
    #     scores.sort(reverse=True)
    #     return scores

    def recommendation_phase(self,target_person):
        if self.target_places_to_users(target_person) == 0:
            print(target_person, "has seen all the places")
            return -1
        not_seen_places,seen_places=self.target_places_to_users(target_person)
        seen_ratings = []
        for places in self.df[target_person]:
            if self.df[target_person][places] == 0.0:
                continue
            seen_ratings.append([self.df[target_person][places],places])
        weighted_avg,weighted_sim = 0,0
        rankings =[]
        for i in not_seen_places:
            for rate,movie in seen_ratings:
                item_sim=self.item_similarity(i,movie)
                weighted_avg +=(item_sim*rate)
                weighted_sim +=item_sim
            if weighted_sim == 0.0:
                weighted_rank = 0.0
            else:
                weighted_rank=weighted_avg/weighted_sim
            rankings.append([weighted_rank,i])

        rankings.sort(reverse=True)
        return rankings

    # def train(self):
    #     rd = copy.deepcopy(self.df)
    #     keyl = list(self.df.keys())
    #     for i in range(len(keyl)):
    #         tp = keyl[i]
    #         print(i, end=' ')
    #         a=self.recommendation_phase(tp)
    #         if a != -1:
    #             for w,m in a:
    #                 rd[tp][m] = w

    #     with open('matrix.pickle','wb') as fw:
    #         pickle.dump(rd, fw)

    #     return

    def eval(self):
        print(self.df)
        if self.target_person in self.df.keys():
            a=self.recommendation_phase(self.target_person)
            if a != -1:
                print("Recommendation Using Item based Collaborative Filtering:  ")
                if len(a) < self.n:
                    return a
                else:
                    return a[:self.total]
        else:
            print("Person not found in the dataset.. or not enough init data, please try again")

    # def adj_recommendation_phase(target_person,dataset):
    #     if target_places_to_users(target_person,dataset) == 0:
    #         print(target_person, "has seen all the places")
    #         return -1
    #     not_seen_places,seen_places=target_places_to_users(target_person,dataset)
    #     seen_ratings = []
    #     for places in dataset[target_person]:
    #         if dataset[target_person][places] == 0.0:
    #             continue
    #         seen_ratings.append([dataset[target_person][places],places])
    #     weighted_avg,weighted_sim = 0,0
    #     rankings =[]
    #     for i in not_seen_places:
    #         temp = []
    #         for rate,movie in seen_ratings:
    #             item_sim=adj_item_similarity(i,movie,target_person,dataset)
    #             heapq.heappush(temp,[-item_sim,rate])
    #         idx=5
    #         while temp and idx > 0:
    #             s,r = heapq.heappop(temp)
    #             weighted_avg +=(-s*r)
    #             weighted_sim +=-s
    #             idx-=1
                
    #         if weighted_sim == 0.0:
    #             weighted_rank = 0.0
    #         else:
    #             weighted_rank=weighted_avg/weighted_sim
    #         rankings.append([weighted_rank,i])

    #     rankings.sort(reverse=True)
    #     return rankings

    def start(self):
        finallist = []
        self.df = self.df.pivot_table('rate',index='place_id',columns='username')
        self.df.fillna(0.0,inplace=True)
        self.df = self.df.to_dict()
        res = self.eval()

        for r in res:
            finallist.append(r[1])

        return finallist

if __name__ == '__main__':
    ## 평균 16~17초 걸림
    ict = ItemCFtrain("갤러리25",3,4)
    start = time.time()
    a = ict.start()
    end = time.time()
    print(end-start)