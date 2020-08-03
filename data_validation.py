# Importing Libraries
import subprocess
from textblob import TextBlob
import nltk
from newspaper import Article
import json


#Enter json file of your code here


#Data fetch
file_data = open('/root/ghatsih/thedominators-f48f2-export.json','r')

#Data conversion to json format
string_of_data = ''
for string_temp in file_data:
    string_of_data = string_of_data + string_temp

dict_of_data = json.loads(string_of_data)

#fetching user id's
users_list = list(dict_of_data['Uploaded Data'].keys())

#initialising comments analysis list
user_positive_ghat = []
user_negetive_ghat = []
user_neutral_ghat = []

user_positive_riverfront = []
user_negetive_riverfront = []
user_neutral_riverfront = []

user_positive_crematory = []
user_negetive_crematory = []
user_neutral_crematory = []



for user in users_list:
	text = dict_of_data['Uploaded Data'][user]['about']
	obj = TextBlob(text)
	#Code for ghat
	if dict_of_data['Uploaded Data'][user]['type'] == 'Ghat':
		
		sentiment = obj.sentiment.polarity
		if sentiment > 0:
			user_positive_ghat.append(user)
		elif sentiment < 0:
			user_negetive_ghat.append(user)
		else:
			user_neutral_ghat.append(user)

	if dict_of_data['Uploaded Data'][user]['type'] == 'Riverfront':

		sentiment = obj.sentiment.polarity
		if sentiment > 0:
			user_positive_riverfront.append(user)
		elif sentiment < 0:
			user_negetive_riverfront.append(user)
		else:
			user_neutral_riverfront.append(user)
	
	if dict_of_data['Uploaded Data'][user]['type'] == "Crematory":
		entiment = obj.sentiment.polarity
		if sentiment < 0:
			user_positive_crematory.append(user)
		elif sentiment > 0:
			user_negetive_riverfront_crematory.append(user)
		else:
			user_neutral_riverfront_crematory.append(user)		   
	
	




if len(user_positive_ghat) == 0:
	user_positive_ghat.append('null')
if len(user_negetive_ghat) == 0:
	user_positive_ghat.append('null')
if len(user_neutral_ghat) == 0:
	user_positive_ghat.append('null')


if len(user_positive_riverfront) == 0:
	user_positive_riverfront.append('null')
if len(user_negetive_riverfront) == 0:
	user_positive_riverfront.append('null')
if len(user_neutral_riverfront) == 0:
	user_positive_riverfront.append('null')

if len(user_positive_crematory) == 0:
	user_positive_crematory.append('null')
if len(user_negetive_crematory) == 0:
	user_positive_crematory.append('null')
if len(user_neutral_crematory) == 0:
	user_positive_crematory.append('null')

	
final_dict = {'Valid_Users_Data':user_positive_ghat,'Invalid_users_Data':user_negetive_ghat,'Data_unidentified':user_neutral_ghat}
final_dict2 = {'Valid_Users_Data':user_positive_riverfront,'Invalid_users_Data':user_negetive_riverfront,'Data_unidentified':user_neutral_riverfront}
final_dict3 = {'Valid_Users_Data':user_negetive_crematory,'Invalid_users_Data':user_positive_crematory,'Data_unidentified':user_neutral_crematory}
print(final_dict)
print('success')
st1 = '</br>'+"\n"+'Data Classified'+"</br>\n" + 'Users with positive comment = ' + str(final_dict['Valid_Users_Data']) + "</br>\n" + 'User with negetive comments =' + str(final_dict['Invalid_users_Data']) + "</br>\n" + 'User with neutral comments = ' + str(final_dict['Data_unidentified']) + "</br>\n\n"
st2 = '-'+"</br>\n"+'Data Classified'+"</br></br>\n\n" + 'Users with positive comment = ' + str(final_dict2['Valid_Users_Data']) + "</br>\n" + 'User with negetive comments =' + str(final_dict2['Invalid_users_Data']) + "</br>\n" + 'User with neutral comments = ' + str(final_dict2['Data_unidentified']) + "</br>\n\n"
st3 = '-'+"</br>\n"+'Data Classified'+"</br></br>\n\n" + 'Users with positive comment = ' + str(final_dict3['Valid_Users_Data']) + "</br>\n" + 'User with negetive comments =' + str(final_dict3['Invalid_users_Data']) + "</br>\n" + 'User with neutral comments = ' + str(final_dict3['Data_unidentified']) + "</br>\n\n"

date_ = subprocess.getoutput("date")
st = "<head>" + "<body>" +'----------------------------------------------'+"<br>"+str(date_)+"</br></br>"+"New DATA"+"</br>"+"Ghats - data"+st1+ "\n"+ "Rvierfront Data" + st2 + "\n" + "Crematory Data"+st3+"</br>" +"</body>" +"</head>"

file2 = open('/root/ghatsih/outpu.txt','w')
file2.write(st)
file3 = open('/var/www/html/index.html','a')
file3.write(st)
