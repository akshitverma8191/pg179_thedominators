# Importing Libraries

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
user_positive = []
user_negetive = []
user_neutral = []


for user in users_list:
	text = dict_of_data['Uploaded Data'][user]['about']
	obj = TextBlob(text)
	sentiment = obj.sentiment.polarity
	if sentiment > 0:
		user_positive.append(user)
	elif sentiment < 0:
		user_negetive.append(user)
	else:
		user_neutral.append(user)    



if len(user_positive) == 0:
	user_positive.append('null')
if len(user_negetive) == 0:
	user_positive.append('null')
if len(user_neutral) == 0:
	user_positive.append('null')

	
final_dict = {'Valid_Users_Data':user_positive,'Invalid_users_Data':user_negetive,'Data_unidentified':user_neutral}
print(final_dict)
st = '\n\n' + 'Users with positive comment = ' + str(final_dict['Valid_Users_Data']) + "\n" + 'User with negetive comments =' + str(final_dict['Invalid_users_Data']) + '\n' + 'User with neutral comments = ' + str(final_dict['Data_unidentified']) + "\n\n"

file2 = open('/root/ghatsih/outpu.txt','w')
file2.write(st)
file3 = open('/var/www/html/index.html','a')
file3.write(st)
