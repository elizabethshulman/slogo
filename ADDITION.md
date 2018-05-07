SLogo Modification for VOOGA Analysis
====
> I plan to work on the back-end extension for SLogo, as it seems simpler to me than the front end extension given our current design.


## Estimation
- I believe this extension will take me an hour or less to complete.
- Ideally, I'll only have to create the commands (as separate classes), then modify the resources to allow for English calls to the commands.
Our back-end was built to be easily extensible with regards to adding commands, so hopefully it should not be too difficult to do that despite 
having done front end throughout the project.

## Review
- It took me about forty-five minutes to complete the feature.
- Overall, I needed to modify the TurtleController; the Stamp and ClearStamp commands initialized during the final sprint; and the
English properties file (though ideally each of the language's respective properties files would be updated to reflect the added stamp functionality).
- I did not get it right. Until I was rereading and about to push the code, my implementation was returning the turtle's index, not the ImageView index.
I also didn't realize initailly that there is an already-implemented method that is capable of returning an ImageView index.


## Analysis
- Especially since I hadn't done back-end, I was pleasantly surprised at how easy it was to build my own command. I knew how to do it, as I'd been
actively involved in the design decision process for it, but it was exciting to actually do it myself.
- Ideally, I'd like to move some of the methods like stamp and clearstamps from the TurtleController. They seem removed from the class's core
functionality, and the class's code is already quite long and clunky.
- If I were not familiar with the code, it would have been more difficult to understand each of the steps required in completing additional commands.