<h3>GETBALANCE</h3>
- GET: localhost:8080/wuphf/player/balance/1

<h3>SUBMIT DEDUCTION(wager)</h3>
- POST: localhost:8080/wuphf/player/deduct

- *{
"transactionId": 16,
"playerId": 1,
"amount": 4000
}*

<h3>SUBMIT DEPOSIT(winning)</h3>
- POST: localhost:8080/wuphf/player/deposit

- *{
"transactionId":10,
"playerId": 1,
"amount": 10000
}*

<h3>FETCH LAST 10 TRANSACTIONS</h3>
- POST: localhost:8080/wuphf/player/lastten

- *{ "playerId": 1,
"password": "swordfish"}*