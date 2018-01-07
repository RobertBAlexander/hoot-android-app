************************************My Hoot App*******************************

Created by:
          Robert Alexander
          02884232

***************************The Hoot App is a lot like a************************
***************************Tweeting App, but it should*************************
***************************only be used by Night Owls!**********************


Features:
-This app is the android side of a Hoot app which can also be accessed through web pages.

-There is no Admin access on the android side of the app, for security reasons.



Splash Screen:
A splash screen lets the user know this app is definitely for owls.



Welcome Screen:
-Users may chose login or signup only from this screen.

-Login and Signup may only be tapped if the app is successfully connecting to the database through
the API.



Login/Signup:
-This App allows a user to signup with their details, and then log in to the app.

-Upon signing up a user enters their
                           -first name
                           -last name
                           -email
                           -password

-the password is hashed and salted for security, and user is added to an online database

-upon login user is asked for their e-mail and password. Password is run through bcrypt
to check hashed and salted password.

-User validation is performed on login. If no user present, they may not login.



Global Timeline:
-After login a user lands on the global timeline.

-This timeline displays all tweets from all users.

-The Timeline is the parent activity for all other activities in the app.

-Hitting back within the Timeline activity sends a user back out to the welcome screen,
and they will no longer be logged in.



Menu:
-From the timeline, and from many other parts of the app, the menu can be accessed.

-The back icon in the menu sends you to the parent activity of the activity you are in.

-The study owl button allows you to view the hoots made by you(personal timeline).

-The blue 'tweet' owl sends you to create a new hoot.

-The menu button displays a dropdown list of all your available menu options. If you select
the activity you are already in, it will refresh the activity, and make a new call to the database
through the web API.

-The settings button allows you to visit the settings activity.

-The map button allows you to view the map activity.

-The All Hoots button sends you to the golbal timeline.

-The Follow Timeline button allows you to view the hoots of the users you follow.

-The Users button allows you to view all users.

-The logout button logs you out of the app, sending you to the welcome activity.



Personal Timeline:
-The personal timeline displays the hoots you have created.



Settings:
-The settings allow you to view your current settings in the app.

-The settings currently do not fully function.



Map:
The map allows you to view a map which displays your location.



All Hoots:
-The All Hoots allows you to view all of the Hoots created so far in across all Hoot Apps.



Follow Timeline:
-The follow timeline allows you to view all the hoots, but only of users that you follow.



Users:
-The users timeline allows you to view all of the users of this app.

-You may choose to follow or unfollow a user here by tapping on their screen, and a button
will automatically be updated.



Hoot Creation(HootOut):
-Up to 140 character Hoots can be created when you tap the Hoot Icon. You may then fill in the
details of your hoot, select a contact to send it to, and then e-mail them. If you hit back
in this screen, no Hoot is created.

-A countdown displays showing you how many characters you have remaining to Hoot. When this
number goes in the minus, you may no longer create the Hoot.

-User may select a contact from their contacts list for this Hoot.

-User can write a Hoot and e-mail it to someone in their phone contacts list(if e-mail is
present in contact info).

-User may Hoot out, if hoot length criteria is met. Then they are returned to the Global Timeline



Persistence:
-JSON was used for both User and Hoot storage. However this was removed so as to store
all data through retrofit calls.

-All data is stored through API calls to the web app.

-All data is stored in mlabs.



Other:
-Permissions were set to be unobtrusive to user.

-User Experience was left with simple blue, and easy to spot buttons in plan area for
clearer use. Visual integration with web version of app was performed through images and
colour choices.

Developer Experience. References have been made to sources of labs and stack overflow
in many cases. Naming conventions have been chosen carefully to make them clear to
understand.

Link to heroku app which the Android uses to connect to database:
https://hoothoot-web-app.herokuapp.com/



Issues:
A number of issues were created between the previous app and this one due to integration issues
between standard integration and fragments. As I was unable to integrate retrofit API calls with
the fragments I had previously, I had to rebuild the app from scratch. However due to time and
other integration issues this prevented me from bringing many of the functions that were
present in my previous version of this app.