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
