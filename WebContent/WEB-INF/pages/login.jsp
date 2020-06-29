<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Login">
    <link rel="shortcut icon" href="assets/img/static/logoColegio.jpg" type="image/x-icon">
    <link rel="stylesheet" href="assets/css/utils/template/font-awesome.css">
    <link rel="stylesheet" href="assets/css/utils/template/ace-fonts.css" />
    <link href="https://fonts.googleapis.com/css2?family=Holtwood+One+SC&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/layout/layout.css">
    <link rel="stylesheet" href="assets/css/base/base.css"> 
    <link rel="stylesheet" href="assets/css/theme/color.css">
    <link rel="stylesheet" href="assets/css/components/button.css">
    <link rel="stylesheet" href="assets/css/components/loader.css">
    <link rel="stylesheet" href="assets/css/utils/animation.css">
    <link rel="stylesheet" href="assets/css/pages/login.css">
    <link rel="stylesheet" href="assets/js/utils/modal/je-modal-style.css">
	<title>Login</title>
</head>
	<body>
		<div class="main">
			<div class="main__left">
				<div class="login">
			        <div class="login__header">
			            <h1 class="login__title">Aula Virtual</h1>
			            <svg class="login__weave" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#438eb9" fill-opacity="1" d="M0,96L6.2,106.7C12.3,117,25,139,37,133.3C49.2,128,62,96,74,90.7C86.2,85,98,107,111,117.3C123.1,128,135,128,148,133.3C160,139,172,149,185,133.3C196.9,117,209,75,222,96C233.8,117,246,203,258,245.3C270.8,288,283,288,295,266.7C307.7,245,320,203,332,192C344.6,181,357,203,369,218.7C381.5,235,394,245,406,245.3C418.5,245,431,235,443,240C455.4,245,468,267,480,272C492.3,277,505,267,517,256C529.2,245,542,235,554,240C566.2,245,578,267,591,250.7C603.1,235,615,181,628,149.3C640,117,652,107,665,101.3C676.9,96,689,96,702,85.3C713.8,75,726,53,738,85.3C750.8,117,763,203,775,224C787.7,245,800,203,812,202.7C824.6,203,837,245,849,245.3C861.5,245,874,203,886,197.3C898.5,192,911,224,923,234.7C935.4,245,948,235,960,213.3C972.3,192,985,160,997,138.7C1009.2,117,1022,107,1034,90.7C1046.2,75,1058,53,1071,53.3C1083.1,53,1095,75,1108,74.7C1120,75,1132,53,1145,42.7C1156.9,32,1169,32,1182,64C1193.8,96,1206,160,1218,160C1230.8,160,1243,96,1255,101.3C1267.7,107,1280,181,1292,197.3C1304.6,213,1317,171,1329,144C1341.5,117,1354,107,1366,106.7C1378.5,107,1391,117,1403,122.7C1415.4,128,1428,128,1434,128L1440,128L1440,0L1433.8,0C1427.7,0,1415,0,1403,0C1390.8,0,1378,0,1366,0C1353.8,0,1342,0,1329,0C1316.9,0,1305,0,1292,0C1280,0,1268,0,1255,0C1243.1,0,1231,0,1218,0C1206.2,0,1194,0,1182,0C1169.2,0,1157,0,1145,0C1132.3,0,1120,0,1108,0C1095.4,0,1083,0,1071,0C1058.5,0,1046,0,1034,0C1021.5,0,1009,0,997,0C984.6,0,972,0,960,0C947.7,0,935,0,923,0C910.8,0,898,0,886,0C873.8,0,862,0,849,0C836.9,0,825,0,812,0C800,0,788,0,775,0C763.1,0,751,0,738,0C726.2,0,714,0,702,0C689.2,0,677,0,665,0C652.3,0,640,0,628,0C615.4,0,603,0,591,0C578.5,0,566,0,554,0C541.5,0,529,0,517,0C504.6,0,492,0,480,0C467.7,0,455,0,443,0C430.8,0,418,0,406,0C393.8,0,382,0,369,0C356.9,0,345,0,332,0C320,0,308,0,295,0C283.1,0,271,0,258,0C246.2,0,234,0,222,0C209.2,0,197,0,185,0C172.3,0,160,0,148,0C135.4,0,123,0,111,0C98.5,0,86,0,74,0C61.5,0,49,0,37,0C24.6,0,12,0,6,0L0,0Z"></path></svg>
			        </div>
			        <div class="login__body">
			        	<div class="login__body-inner">
			        		<div class="login__img-container">
				            <img src="assets/img/static/logoColegio.jpg" alt="" class="login__img">
				        </div>
			            <form class="login__form form" id="form-login">
				                <div class="form__group">
				                    <div class="input-icon">
				                    	<i class="input-icon__icon ace-icon fa fa-user"></i>
				                        <input type="text" name="username" class="input-icon__input" placeholder="Usuario" required>
				                        <div class="icon-error">
				                        	<i class="icon-error__icon fas fa-exclamation-circle"></i>
				                        	<div class="icon-error__info">
				                        		<span class="icon-error__info-text">Campo requerido</span>
				                        	</div>
				                        </div>
				                    </div>
				                </div>
				                <input type="hidden" name="action" value="acceder">
				                <div class="form__group form__group--mb-4">
				                    <div class="input-icon">
				                    	<i class="input-icon__icon ace-icon fas fa-lock"></i>
				                        <input type="password" name="password" class="input-icon__input" required placeholder="Contraseña">
				                        <div class="input-icon__view">
				                        	<i class="input-icon__icon fas fa-eye-slash"></i>
				                        	<!--<i class="input-icon__icon fas fa-eye"></i>-->	
				                        </div>
				                        <div class="icon-error">
				                        	<i class="icon-error__icon fas fa-exclamation-circle"></i>
				                        	<div class="icon-error__info">
				                        		<span class="icon-error__info-text">Campo requerido</span>
				                        	</div>
				                        </div>
				                    </div>
				                </div>
				                <button class="login__button je-btn je-btn--fill je-btn--upper je-btn--shadow" type="submit">Ingresar</button>
				                <div class="login__reset-pass txt-align-c">
					                <!--<a class="login__pass" href="">Olvidó su contraseña</a>-->
				                </div>
				            </form>
				        </div>
			        </div>
			    </div>
			</div>
			<div class="main__right">
				<div class="video-response">
					<video class="video-response__video" muted autoplay loop>
			  			<source src="assets/video/video_login.mp4" type="video/mp4">
			  		</video>	
				</div>
				<h1 class="main__title">Aula Virtual</h1>
			</div>	
		</div>
    	 <script src="https://kit.fontawesome.com/56e0c4d4ed.js"
		crossorigin="anonymous"></script>
		<script src="assets/js/utils/modal/je-modal.js"></script>
		<script src="assets/js/utils/modal/modalMessage.js"></script>
		<script src="assets/js/utils/Api.js"></script>
    	<script src="assets/js/generic/loader.js"></script>
    	<script src="assets/js/pages/login.js"></script>
	</body>
</html>