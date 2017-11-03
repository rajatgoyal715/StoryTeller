from flask import Flask,request,render_template,send_file
import requests
import json
from bs4 import BeautifulSoup
from gtts import gTTS
import test

app = Flask(__name__)




@app.errorhandler(404)
def page_not_found(e):
    return render_template('404.html'), 404


@app.errorhandler(501)
def page_not_found(e):
    return render_template('503.html'), 501


@app.errorhandler(500)
def page_not_found(e):
    return render_template('404.html'), 500


@app.errorhandler(503)
def page_not_found(e):
    return render_template('404.html'), 503






@app.route('/x',methods=['GET','POST'])
def action(url=""):
    if request.method == 'GET':
        url = request.args.get('url')
        print "url is "+url
        #links = []
        #links.append(url)
        #print links
        try:
            return_this = test.medium(article_links=url)
            print "return this"
            return render_template('index.html',x=str(return_this))
            #return return_this

        except:
            #app.errorhandler(404)
            print "404"
            return "404"


        #return in medium()













#if __name__ == "__main__":
#    app.run()


