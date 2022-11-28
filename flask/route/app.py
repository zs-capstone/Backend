from flask      import Flask, request, jsonify, current_app
from flask.json import JSONEncoder
from sqlalchemy import create_engine, text
from route.train_code.item_CF_train_code import ItemCFtrain
import csv

app = Flask(__name__)

def get_star_reviews():
    reviews = current_app.database.execute(text("""
        SELECT 
            username,
            place_id,
            rate
        FROM place_review
    """),{}).fetchall()

    fl = []
    for r in reviews:
        fl.append({
            'username'      : r['username'],
            'place_id'    : r['place_id'],
            'rate'   : r['rate']
        })

    with open("output.csv","w",newline="") as f:
        title = "username,place_id,rate".split(",")
        cw = csv.DictWriter(f,title,delimiter=',', quotechar='|', quoting=csv.QUOTE_MINIMAL)
        cw.writeheader()
        cw.writerows(fl)

    return

@app.route('/', methods=['GET'])
def health_check():
    return "ai 정상 작동"

@app.route('/eval', methods=['POST'])
def eval():
    data = request.get_json()
    orglist = data['orgPlaceList']
    username = data['username']
    day = data['day']
    n = data['n']

    get_star_reviews()
    ict = ItemCFtrain(username,int(day),int(n))
    a = ict.start()

    ex = []
    for org in orglist:
        if org not in a:
            ex.append(org)

    a = ex + a[:len(a)-len(ex)]
    exlen = len(ex)
    fl = []
    for idx,elem in enumerate(a):
        if idx < exlen:
            flag = True
        else:
            flag = False
        fl.append({
            "placeId": elem,
            "isUserPick": flag,
            "day": idx//n +1,
        })

    return jsonify({"listEvalResponse":fl})

# if __name__ == '__main__':
#     app.config.from_pyfile("./config/config.py")
	
#     # 데이터 베이스와 연동해준다.
#     database = create_engine(app.config['DB_URL'], encoding = 'utf-8', max_overflow = 0)
#     app.database = database
#     app.run(host='0.0.0.0', debug=True, port=6000)

# if __name__ == 'uwsgi_file___route_app':
#     app.config.from_pyfile("./config/config.py")
        
#     database = create_engine(app.config['DB_URL'], encoding = 'utf-8', max_overflow = 0)
#     app.database = database
#     app.run(host='0.0.0.0', debug=True, port=6000)

app.config.from_pyfile("./config/config.py")
        
database = create_engine(app.config['DB_URL'], encoding = 'utf-8', max_overflow = 0)
app.database = database
app.run(host='0.0.0.0', debug=True, port=6000)