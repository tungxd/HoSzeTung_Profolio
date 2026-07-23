<!--SEHS4517 Web Application Development and Management-->
<!--Individual Assignment-->
<!--7 March 2025-->
<!--Ho Sze Tung-->
<!--24055226S-->
<!DOCTYPE html>
<html>
	<head>
		<title>Check Reservation</title>
	</head>
	<body background="sport.jpg" style="background-repeat: no-repeat; background-size: cover">
	<?php
		$firstname = $_POST['firstname'];
		$lastname = $_POST['lastname'];
		$gender = $_POST['gender'];
		$datein = $_POST['datein'];
		$timein = $_POST['timein'];
		$item = $_POST['item'];
		$address = $_POST['address'];
		$telin = $_POST['telin'];
		$emailin = $_POST['emailin'];
		$username = $_POST['username'];

		$reservationDateTime = DateTime::createFromFormat('Y-m-d H:i', $datein . ' ' . $timein);
		$currentDateTime = new DateTime();

		$interval = $currentDateTime->diff($reservationDateTime);
		$hoursDifference = ($interval->days * 24) + $interval->h;

		if ($hoursDifference >= 24 && $hoursDifference <= 72) {
			echo "<html>
					<head>
						<title>Thank You</title>
						<style>
							body { background-color: #BEEEFB; text-align: center; }
							h1 { color: BlueViolet; }
							p { font-size: 20px; }
						</style>
					</head>
					<body>
						<h1>Thank You!</h1>
						<p>Your reservation has been successfully confirmed. We look forward to serving you!</p>
					</body>
				  </html>";
		} elseif ($hoursDifference < 24) {
			echo "<html>
					<head>
						<title>Sorry</title>
						<style>
							body { background-color: #FFCCCB; text-align: center; }
							h1 { color: red; }
							p { font-size: 20px; }
						</style>
					</head>
					<body>
						<h1>Sorry!</h1>
						<p>Reservations must be made at least one day in advance.</p>
					</body>
				  </html>";
		} else {
			echo "<html>
					<head>
						<title>Sorry</title>
						<style>
							body { background-color: #FFCCCB; text-align: center; }
							h1 { color: red; }
							p { font-size: 20px; }
						</style>
					</head>
					<body>
						<h1>Sorry!</h1>
						<p>Reservations can only be made up to three days in advance.</p>
					</body>
				  </html>";
		}
	?>
	<br/>
	<br/>
	<br/>
	<form method="post" action="reserve.html">
		<button type="submit" id="back" 
		style="background-color:BlueViolet; color: white; border-radius:5px; border:none; cursor:pointer; font-size: 20px; 
		padding: 10px 20px; font-family:Times New Romance">Back to Main Page</button>
	</form>
	</body>
</html>	