# bjplayer

A BlackJack playing experiment.

```
Usage: Main [-v] [rounds] [iterations]

By default 10 rounds of 1000000 BlackJack games will be played using different strategies.
Output will contain total bet amount, win amount and win percentage.

Optionally -v will enable verbose output to stderr.
Giving -h or --help as first argument will result displaying usage instructions.
```

Without arguments Main class will run 10 rounds of 1000000 iterations with each strategy.

Example winning percentage is expected to differ by about 0.01%, as can be summarized in following graph:
![Sample output from running 50 rounds of 1000000 iterations](sample-output-win-percentages.png)
