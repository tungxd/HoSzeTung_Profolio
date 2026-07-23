<!--SEHS4517 Web Application Development and Management-->
<!--Individual Assignment-->
<!--7 March 2025-->
<!--Ho Sze Tung-->
<!--24055226S-->
<!DOCTYPE html>
<html>
	<head>
		<title>Confirm Reservation</title>
		<style>
		.information {
			color:#f55a02;
			display:inline-block;
		}
		</style>
	</head>
	<body background="sport.jpg" style="background-repeat: no-repeat; background-size: cover">
		<h1 style="text-align:center; color: BlueViolet">Reservation Confirmation</h1>
		<h2 style="text-align:center; color: Blue">Your reservation information</h2>
		<div style="border-radius: 10px; background:#BEEEFB; box-shadow: 0 0 5px rgba(0, 0, 0, 1.1); margin-left:auto; margin-right:auto; width:50%">
		<table width="100%" style="margin-left: auto; margin-right: auto; font-size:20px; text-align:center" >
			<tr height="70px" >
				<td>First Name: <div class="information"><?php echo $_POST['firstname']; ?></div></td>
				<td>Last Name: <div class="information"><?php echo $_POST['lastname']; ?></div></td>
			</tr>
			<tr height="70px">
				<td>Gender: <div class="information"><?php echo $_POST['gender']; ?></div></td>
				<td>Date of Reservation: <div class="information"><?php echo $_POST['datein']; ?></div></td>
			</tr>
			<tr height="70px">
				<td>Time of Reservation: <div class="information"><?php echo $_POST['timein']; ?></div></td>
				<td>Reserved Item: <div class="information"><?php echo $_POST['item']; ?></div></td>
			</tr>
			<tr height="70px">
				<td>Mailing Address: <div class="information"><?php echo $_POST['address']; ?></div></td>
				<td>Phone Number: <div class="information"><?php echo $_POST['telin']; ?></div></td>
			</tr>
			<tr height="70px">
				<td>Email Address: <div class="information"><?php echo $_POST['emailin']; ?></div></td>
				<td>Member Username: <div class="information"><?php echo $_POST['username']; ?></div></td>
			</tr>
		</table>
		</div>
		<br/>
		<br/>
		<div style="text-align:center;">
			<form method="post" action="check.php" style="width:10%; display: inline-block">
				<input type="hidden" name="firstname" value="<?php echo $_POST['firstname']; ?>">
				<input type="hidden" name="lastname" value="<?php echo $_POST['lastname']; ?>">
				<input type="hidden" name="gender" value="<?php echo $_POST['gender']; ?>">
				<input type="hidden" name="datein" value="<?php echo $_POST['datein']; ?>">
				<input type="hidden" name="timein" value="<?php echo $_POST['timein']; ?>">
				<input type="hidden" name="item" value="<?php echo $_POST['item']; ?>">
				<input type="hidden" name="address" value="<?php echo $_POST['address']; ?>">
				<input type="hidden" name="telin" value="<?php echo $_POST['telin']; ?>">
				<input type="hidden" name="emailin" value="<?php echo $_POST['emailin']; ?>">
				<input type="hidden" name="username" value="<?php echo $_POST['username']; ?>">
				
				<button type="submit" id="submit" 
				style="background-color:BlueViolet; color: white; border-radius:5px; border:none; cursor:pointer; font-size: 20px;
				padding: 10px 20px;  font-family:Times New Romance">Confirm</button>
			</form>
			
			<form method="post" action="reserve.html" style="width:10%; display: inline-block">
			<button type="submit" id="back" 
			style="background-color:BlueViolet; color: white; border-radius:5px; border:none; cursor:pointer; font-size: 20px; 
			padding: 10px 20px; font-family:Times New Romance">Back</button>
			</form>
		</div>
	</body>
</html>	