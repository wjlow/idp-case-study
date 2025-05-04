# Assumptions

I made some assumptions while understanding the case study brief. 

## A user's persona is stored in Auth0

The brief describes:

> The new application will show a single option as “Sign in with your Seek account” for both recruiters and candidates,
**depending on the selected persona by user**.

and

> We are not after assessing your frontend skills in any shape; **a single action on the application page that can
call a single endpoint on the backend would be sufficient**.

This reads to me like the requirement does not require a feature for the user to select their persona when logging in.
Instead, I am assuming that the user's persona is stored in Auth0 and known beforehand. 

## There is no need to build out the Recruiter experience

The brief describes:

> After successfully logging in, the front-end application needs to securely communicate with a back-end
application on behalf of the user. This back-end would be used to provide **the candidate** with a list of their upcoming
interviews.

It appears that there is no requirement to list interviews that a **recruiter** has scheduled on behalf of candidates.

To demonstrate my understanding of different user personas/roles, I print out a log message containing a user's
role (e.g. CANDIDATE or RECRUITER) in the backend when retrieving their interviews. 