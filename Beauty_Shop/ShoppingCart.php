<html>
	<head>
		<title>Shopping Cart</title>
		<style>
		body h1 {
			color:BlueViolet;
			text-align:center;
		}
		
		body {
            margin: 0px;
            font-size: 16px;
            font-family: didot;
        }
		
		.payment-section {
            margin-top: 20px;
            text-align: center;
        }
		
		td, th {
			text-align:center !important;
			padding: 10px;
		}
		
		table, td, th {
			border: 1px solid black !important;
		}	
		
		table {
			border-collapse: collapse !important; 
			width: 95%;
			margin: 10px 0;
			margin-left: 50px;
    }
		
		.buy-button {
			margin-top: 20px;
            text-align: center;
        }
		
		.buy-button input {
			font-size: 20px;
            padding: 10px;
            background-color: BlueViolet;
            color: white;
            border: none;
            cursor: pointer;
			border-radius: 5px;
			text-decoration:none;
        }
		
        .buy-button input:hover {
            background-color: darkmagenta;
			text-decoration:none;
        }
		
		.delete {
			font-size: 16px;
            padding: 10px;
            background-color: cc4105;
            color: white;
            border: none;
            cursor: pointer;
			border-radius: 5px;
			text-decoration:none;
		}	
		</style>
	</head>
	<body>
		<div w3-include-html="navibar.html"></div>	
	<?php
	include "dbConnect.php";
	session_start();

	$products = [
		'Basic Facial' => 380,
		'Ultra-sound Acne Treatment' => 650,
		'Aromatic Treatment' => 700,
		'Diamond Peel Facial Treatment' => 850,
		'Girl Line Treatment' => 880,
		'Body Relaxation Massage (45 Mins)' => 290,
		'Whitening Hand Treatment' => 280,
		'Basalt Stone Massage' => 400,
		'Back Whitening Treatment' => 600,
		'Lymphatic Drainage Massage (45 Mins)' => 390,
		'Weightless Skin Foundation SPF 15' => 500,
		'Skin Concealer Stick' => 450,
		'Mini Highlighting Powder' => 680,
		'Pot Rouge for Lips & Cheeks' => 300,
		'Bronzing Powder' => 400,
		'Babor Gentle Cleansing Cream 100ml (Retail)' => 168,
		'Elemis Soothing Apricot Toner 200ml' => 180,
		'Thalgo Reviving Marine Mist 250ml (Salon)' => 280,
		'Decleor Sweet Orange Sleeping Mask 50ml' => 250,
		'Decleor Lavender Iris Aromessence Serum 15ml (Retail)' => 380,
		'Eyebrow Shaping' => 120,
		'Belly Candle' => 250,
		'Shoulder Massage' => 130,
		'Eyelash Perm' => 480,
		'Make Up' => 290
	];
	
	// Handle deletion of items from the cart
		if (isset($_POST['delete'])) {
			$product_to_delete = $_POST['delete'];
			if (isset($_SESSION['cart'][$product_to_delete])) {
			unset($_SESSION['cart'][$product_to_delete]);
			}
		}
	
	// Process form submission
	if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['treatment'], $_POST['quantity'])) {
		$selected_treatment = $_POST['treatment'];
		$quantity = (int)$_POST['quantity'];
		
		// Customer details
		$customer_name = $_POST['name'];
		$customer_phone = $_POST['phone'];
		$customer_email = $_POST['email'];
		$customer_age = $_POST['age'];
		$customer_gender = $_POST['gender'];
		$appointment_date = $_POST['date'];
		$appointment_time = $_POST['time'];
		$remark = $_POST['remark'];

		// Store customer details in the session
		$_SESSION['customer'] = [
			'name' => $customer_name,
			'phone' => $customer_phone,
			'email' => $customer_email,
			'age' => $customer_age,
			'gender' => $customer_gender,
			'date' => $appointment_date,
			'time' => $appointment_time,
			'remark' => $remark
		];
		
		if (array_key_exists($selected_treatment, $products) && $quantity > 0) {
            $_SESSION['cart'][$selected_treatment] = [
                'name' => $selected_treatment,
                'price' => $products[$selected_treatment],
                'quantity' => $quantity
            ];
			echo"<script>alert('Item Already Added');</script>";
		} else {
			echo "<script>alert('Invalid treatment or quantity');</script>";
			}
	}
		
	
	// Calculate total quantity of items in the cart
	$total_items_in_cart = isset($_SESSION['cart']) ? array_sum(array_column($_SESSION['cart'], 'quantity')) : 0;
	?>

	<h1>Shopping Cart</h1>
	<?php if (!empty($_SESSION['cart'])): ?>
		<form method="post" action="http://localhost/ShoppingCart.php">
		<table border="1">
			<tr>
				<th>Product</th>
				<th>Price (HK$)</th>
				<th>Quantity</th>
				<th>Total (HK$)</th>
				<th>Remove products/treatments</th>
			</tr>
			<?php
			$total_price = 0;
			foreach ($_SESSION['cart'] as $item) {
				$item_total = $item['price'] * $item['quantity'];
				echo "<tr>";
				echo "<td>{$item['name']}</td>";
				echo "<td>{$item['price']}</td>";
				echo "<td>{$item['quantity']}</td>";
				echo "<td>{$item_total}</td>";
				echo "<td>
						<form method='post' action='ShoppingCart.php'>
                        <input type='hidden' name='delete' value='{$item['name']}'>
                        <button type='submit' class='delete'>Delete</button>
						</form>
						</td>";
				echo "</tr>";
				$total_price += $item_total;
			}
			?>
			<tr>
				<td colspan="3">Total Price</td>
				<td><?php echo $total_price; ?></td>
			</tr>
		</table>
		</form>
		
		 <form method="post" action="thankyou.php">
		 <div class="payment-section">
            Select Payment Method:
            <select name="payment_method" id="payment-method" required>
                <option value="credit_card">Credit Card</option>
				<option value="PayMe">PayMe</option>
                <option value="Alipay">Alipay</option>
                <option value="bank_transfer">Bank Transfer</option>
            </select>
        </div>
			<?php foreach ($_SESSION['cart'] as $item): ?>
				<input type="hidden" name="items[]" value="<?php echo htmlentities(json_encode($item)); ?>">
			<?php endforeach; ?>
			<input type="hidden" name="totalAmount" value="<?php echo $total_price; ?>">
			<div class="buy-button">
				<input type="submit" name="buy_now" value="Buy now">
			</div>
		</form>
		
	<?php else: ?>
		<p>Your cart is empty.</p>
	<?php endif; ?>
	
	</body>
	<div w3-include-html="footer.html"></div>
	<script src="https://www.w3schools.com/lib/w3.js"></script>
    <script>
        w3.includeHTML();
    </script>
</html>