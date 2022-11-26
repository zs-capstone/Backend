from flask import Flask

app = Flask(__name__)

@app.route('/', methods=['GET'])
def index():
    return "ai 정상 작동"

# @app.route('/ai', methods=['GET'])
# def index():
#     return "ai 정상 작동"
#
# @app.route('/ai', methods=['GET'])
# def index():
#     return "ai 정상 작동"

if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=True, port=6000)