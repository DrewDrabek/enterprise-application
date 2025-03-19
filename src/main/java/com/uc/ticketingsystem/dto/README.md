# Notes

"Holds data structures used to transfer data between layers of your application ( DTOs often have a different structure than your database entities."

It seems that this is used for selecting what data is sent to the frontend instead of just interacting directly with the entity 

technically these are not needed but they are considered best practice for security and for decoupling.

We are going to use these and since we are not going to manage users internally and we will use a third party for user management we will most likely wont need a dto for this. 


For the user management we need to figure out how to map the user ids to each other maybe we make a field in the token for the user guid that way we can pass back who is making what.