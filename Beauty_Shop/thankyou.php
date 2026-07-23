<?php
	session_start();
	include "dbConnect.php";
	
	// Ensure the cart is not empty
	if (!isset($_SESSION['cart']) || empty($_SESSION['cart'])) {
		echo "<script>alert('Your cart is empty.'); window.location.href = 'ShoppingCart.php';</script>";
		exit;
	}
	
	if ($_SERVER['REQUEST_METHOD'] == 'POST') {
		// Validate and store payment method
		if (isset($_POST['payment_method']) && in_array($_POST['payment_method'], ['credit_card', 'PayMe', 'Alipay', 'bank_transfer'])) {
			$_SESSION['customer']['payment_method'] = $_POST['payment_method'];
		} else {
			echo "<script>alert('Please select a valid payment method.'); window.location.href = 'ShoppingCart.php';</script>";
			exit;
		}
	}
	
	// Retrieve customer details from the session
	$customer = $_SESSION['customer'];
	$paymentMethod = $customer['payment_method'] ?? null;
	
	// Ensure all required fields are present
	if (!isset($customer['name'], $customer['phone'], $customer['email'], $paymentMethod)) {
		echo "<script>alert('Missing customer details or payment method.'); window.location.href = 'ShoppingCart.php';</script>";
		exit;
	}
	
	function generateUniqueOrderNumber($linkID) {
		do {
			$orderNumber = "ORD" . time() . rand(1000, 9999);
			$query = "SELECT COUNT(*) FROM client_order WHERE order_number = '$orderNumber'";
			$result = mysqli_query($linkID, $query);
			$row = mysqli_fetch_array($result);
		} while ($row[0] > 0); // Repeat if a duplicate is found

		return $orderNumber;
	}
	
	// Generate a unique order number
	$orderNumber = generateUniqueOrderNumber($linkID);
	
	// Insert the order into `client_order` table
	$orderQuery = "INSERT INTO client_order (order_number, name, telephone, address, payment_method)
        VALUES ('$orderNumber', '{$customer['name']}', '{$customer['phone']}', '{$customer['email']}', '$paymentMethod')";

	if (!mysqli_query($linkID, $orderQuery)) {
		error_log("Error inserting order: " . mysqli_error($linkID));
		echo "<script>alert('Failed to create order.'); window.location.href = 'ShoppingCart.php';</script>";
		exit;
	}
	
	// Insert each cart item into the `order_detail` table
	foreach ($_SESSION['cart'] as $item) {
		$productID = $orderNumber . "-PROD" . time() . rand(100, 999);
		$productQty = $item['quantity'];
		$subtotal = $item['price'] * $item['quantity'];

		$orderDetailQuery = "INSERT INTO order_detail (order_code, product_id, product_qty, subtotal)
            VALUES ('$orderNumber', '$productID', $productQty, $subtotal)";
		
	}
			
	// Retrieve cart items and calculate total amount
	$items = $_SESSION['cart'];
	$totalAmount = 0;

	if (!empty($items)) {
		foreach ($items as $item) {
			$totalAmount += $item['price'] * $item['quantity'];
		}	
	}
	
	// Clear the cart and customer session
	unset($_SESSION['cart']);
	unset($_SESSION['customer']);
	?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thank you for your purchase</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            text-align: center !important;
        }
        h1 {
            color: #4CAF50;
			text-align:center;
        }
		
        .thank-you {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            display: inline-block;
			border-radius: 8px;
			text-align: center;
        }
        .order-details {
            margin-top: 20px;
            text-align: left;
        }
    </style>
</head>
<body>
	<div w3-include-html="navibar.html"></div>
        <div class="thank-you" ">
            <h1 style="color: purple;">Thank you for your purchase！</h1>
            <p style="font-size: 18px; color: #555;">Your order has been successfully processed.</p>
            <div class="order-details" style="margin-top: 20px;">
                <h2 style="color: #333;">Order details：</h2>
				<?php if (!empty($items)) ?>
					<?php foreach ($items as $item): ?>
					<p><?php echo htmlentities($item['name']); ?> (x<?php echo $item['quantity']; ?>): $<?php echo $item['price'] * $item['quantity']; ?></p>	
				<?php endforeach; ?>
				<p style="font-weight: bold;">Total：<span style="color: #D32F2F;">$<?php echo $totalAmount; ?></span></p>
            </div>
            <p style="font-size: 16px; color: #777;">We will send the items to you as soon as possible!</p>
        </div>
	<div w3-include-html="footer.html"></div>
    <script src="https://www.w3schools.com/lib/w3.js"></script>
    <script>
        w3.includeHTML();
    </script>
</body>
</html>