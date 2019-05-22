![Abyssinian](http://www.pethealthnetwork.com/sites/default/files/styles/large/public/the-abyssinian.jpg "Abyssinian Cat")

The codename indicates the order of the accepted challenges. Here comes the first one.

[![CircleCI](https://circleci.com/gh/zh3w4ng/abyssinian.svg?style=svg)](https://circleci.com/gh/zh3w4ng/abyssinian)

# Installation

sbt 1.2.8
scala 2.12.8
scalatest 3.0.5
scalafmt 2.0.0

# Run and Test

`sbt run "Bras Basah" "City Hall" "2019-01-31 16:00"` to run any query and you will get the following output
```
[info] Running solution.Main Bras Basah City Hall 2019-01-31 16:00
Found 3323 route(s).
Shortest distance is 3
CC2(Bras Basah) -> CC1(Dhoby Ghaut)
CC1(Dhoby Ghaut) -> NS24(Dhoby Ghaut)
NS24(Dhoby Ghaut) -> NS25(City Hall)
```

`sbt test` to run unit tests. And here is the test result.
```
[info] MainSpec:
[info] msg
[info] - should equal to hello
[info] TrainSpec:
[info] codeName
[info] - should equal to EE
[info] interchangeStationsWithTrain
[info] - should return all interchange stations between EW and CC
t[info] RoutePlannerSpec:
[info] trains
[info] - should have 8 trains
[info] - should have 166 stops in total
[info] allStations
[info] - should have 119 distinct stations
[info] - should have 143 stations including interchanges for different lines
[info] allRoutesFor
[info] - should find a direct route between Pasir Ris and City Hall
[info] - should find a one-transfer route between Holland Village to Bugis
[info] Run completed in 1 second, 29 milliseconds.
[info] Total number of tests run: 9
[info] Suites: completed 3, aborted 0
[info] Tests: succeeded 9, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[success] Total time: 3 s, completed 23 May, 2019 1:57:06 AM
```
# Issues and Future Work

There is a broken record in the raw csv input file, and the checked-in file in this repo is the fixed version. I tried to finish the bonus task but came to realize I would not benefit much from it. That's because I tend to write succinct code in a funcional way. But the scenario is just realistic and has too many branches. It's better done in a procedural way. Another thing you may notice is there're very little comment in the sourcecode. It's mainly because the logic/calculate is easy to reason about and comments would cause confusion on the contrary.

I would give it another try over the week if I have time. I'm just afraid it will uglify my code. Better to show the cleaner codebase to you anyways. That's a great challenge and I enjoy it very much.

