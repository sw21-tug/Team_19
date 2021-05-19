# Team_19

Product Owner: Sebastian Überreiter\
Scrum Master: Lukas Neuhold\
Senior Developer: Dominik Dieter Gruber\
Senior Developer: Fabian Rinesch\
Developer: Gregor Potthast\
Developer: Oana-Emilia Bodirnea\
Developer: Ronald Giegerl\
Developer: Markus Köstl

QUIZLET

1. Profile and settings

The basic idea is a quizapp as general known. 
The user will be able to create his own profile. Said profile consists of a username which can be set and changed by the user accordingly, a picture representing the user and a high score list of how many questions he could answer in a row. 

2. Questions and categories

A basic set of questions for multiple categories will be implemented in the app and is arbitrarily expandable by the user. He/She/the person can add, update or delete certain question. Hence, the user is also responsible for the correctness of his added questions and answers. It is also possible for the user to create question sets, which can be given to other users for them to try their best there. High scores and points will be set according to the users success. 

The questions will be added into certain categories. Say, a question “What type of Tree is most common in the German black forest?”, will be added to the category nature. Categories and questions are expandable by the user but also by the developer Team. Basic implemenation will contain the following categories: 

FAUNA & FLORA
MUSIC
FILM & TV
ANIMALS

Different categories can also be recognized by different colors in the app. 

3. Navigation and Style

The app will consist of 4-5 simple screens where the user can change inbetween. A basic intro screen will greet the user with the name of the app and an option to log in or create an account.
After succesfully loging in or creating an account the user will be sent to a basic homescreen where he can decide to either play a random category or filter a category of questions to try and set an highscore there. A corner will contain a simple option to edit the account or edit the questions/answers. Therefore it will contain an search option to filter out a question with the corresponding answers the user wants to change. 

4. Play style and workflow of the app
Once the user has choosen a category he wants to play (or choosen the random category), the screen will change and the first question will be displayed to the user. Questions are randomly choosen and the sequence will always change so the user can’t memorize a pattern or it will be too easy. After successfully answering a question, the user will be rewarded with a point which will add to his highscore. The highscore will be displayed all the time while the user plays so he knows how far he has already gotten. If a question is answered wrong, the app will display a popup message to the user consisting of a small text congratulating the user for getting so far and the number of points he has achieved. After closing the message, the user will be sent back to the home screen and the high score will be saved in his account. High scores are individually saved for each user. 

Currently Implementing:\
QL-005 Highscore\
QL-019 Gameplay Frontend Extended\
QL-021 Language support for question database\

Already implemented:\
QL-015 Play logic behind intro screen\
QL-014 Login Logic behind Intro Screen\
QL-010 Gameplay Frontend\
QL-011 User Account Backend QL-011\
QL-003 Question Database QL-003\
QL-001 Intro Screen QL-001\
