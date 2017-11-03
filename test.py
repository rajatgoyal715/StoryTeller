# -*- coding: utf-8 -*-
'''class myOpener(FancyURLopener):
	version = 'Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11'
openurl = myOpener().open'''

# page = BeautifulSoup(openurl(url).read().decode('utf-8', 'ignore'), "lxml", parse_only=SoupStrainer('main'))




from bs4 import BeautifulSoup
from gtts import gTTS
import requests
import dropbox
import json
from flask import jsonify
#article_links = sys.argv
import uuid
import string
from random import *


dbx = dropbox.Dropbox('JBU9V-MAD5IAAAAAAAAKFyv7qrsfPiTaY6N15M6lDDoPXpbPiNcMxKYELk_IlnKb')

dbx.users_get_current_account()


def random_string(min_char=3, max_char=5, number_of_words=1):

    allchar = string.ascii_letters
    if number_of_words==1:
        word = "".join(choice(allchar) for x in range(randint(min_char, max_char)))
        return word
    elif number_of_words>1:

        for i in range(0,number_of_words):
            # if number_of_words % 2 == 0:
            #
            # else: space = '\0'
            space = ' '
            word = "".join(choice(allchar) for x in range(randint(min_char, max_char)))
            new_word = '\0'
            #new_word = str(word + ' '+ word)
            for i in range(0,number_of_words):
                word = "".join(choice(allchar) for x in range(randint(min_char, max_char)))
                if new_word == '\0':
                    new_word = str( str(word))

                else:
                    new_word = str(new_word + space + str(word))
        return new_word.title()





def temp_link(path='/test/name.mp3'):
    url = "https://api.dropboxapi.com/2/files/get_temporary_link"

    headers = {
        "Authorization": "Bearer JBU9V-MAD5IAAAAAAAAKGAPhOoo_427z2iTnHHH6RP4UxYBzOBhZkzbbftBZCllj",
        "Content-Type": "application/json"
    }

    data = {
        "path": path
    }

    r = requests.post(url, headers=headers, data=json.dumps(data))
    return json.loads(r.content)["link"]



def medium(article_links):



            url = article_links
            print url
            r = requests.get(url)
            page = BeautifulSoup(r.text,"lxml")

            content = []
            title_name = ""
            for title in page.find_all('h1', {'class': 'graf'}):
                article_title = 'Title: ' + title.text
                title_name = title.text
                print title_name
                try:
                    content.append(article_title)
                except:
                    print "no title"
            for paragraphs in page.find_all('p', {'class': 'graf'}):
                content.append(paragraphs.text)
                #print content
            text_to_speech = '\n\n'.join(content)
            tts = gTTS(text=text_to_speech, lang='en-au')
            #name = str(''.join([i for i in title_name if i.isalpha()]))
            name = str(title_name.encode('utf-8'))
            print "name"+name
            if name is "":
                name = random_string()
            tts.save(name + ".mp3")
            url = "https://content.dropboxapi.com/2/files/upload"
            y = name+'.mp3'
            print "y is " + y
            db_path = "TEST/"+y
            print "db_path "+db_path
            print str('{\"path\":\"'+db_path+'\"}')

            headers = {
                "Authorization": "Bearer JBU9V-MAD5IAAAAAAAAKGAPhOoo_427z2iTnHHH6RP4UxYBzOBhZkzbbftBZCllj",
                "Content-Type": "application/octet-stream",
                "Dropbox-API-Arg": "{\"path\":\"/TEST/"+db_path+"\"}"
                #"{\"path\":\"\"}"
            }
            print headers

            #data = open("/Users/sameer18051998/PycharmProjects/Medium_to_Sound/10 Reasons Python is Awesome.mp3", "rb").read()
            data = open("/Users/sameer18051998/PycharmProjects/Medium_to_Sound/"+name+".mp3","rb").read()

            r = requests.post(url, headers=headers, data=data)

            path = json.loads(r.content)['path_display']
            print path

            db_link = temp_link(path)

            #print db_link
            rz = json.dumps({"url":db_link})
            #print rz
            return rz
# #
#url="https://medium.com/@joshdotai/10-reasons-python-is-awesome-3dcb98a1291a"
# links = []
# links.append(url)
#return_this = medium(article_links=url)
#print "return this"+return_this




#medium(article_links = ["https://medium.com/@joshdotai/10-reasons-python-is-awesome-3dcb98a1291a"])





















'''            with open(name+".mp3", 'wb') as f:
                gTTS.write_to_fp(f)
                data = f.read()
                with:
                    res = dbx.files_upload(
                        data, path, mode,
                        client_modified=datetime.datetime(*time.gmtime(mtime)[:6]),
                        mute=True)
                f.close()

            with open("/Users/sameer18051998/PycharmProjects/Medium_to_Sound/10 Reasons Python is Awesome.mp3", 'rb') as f:
                data = f.read()
'''