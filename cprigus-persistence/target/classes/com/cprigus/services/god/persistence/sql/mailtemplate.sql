# Host: localhost  (Version: 5.5.45)
# Date: 2015-10-22 12:06:03
# Generator: MySQL-Front 5.3  (Build 4.224)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "mailtemplate"
#

DROP TABLE IF EXISTS `mailtemplate`;
CREATE TABLE `mailtemplate` (
  `ID_TEMPLATE` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `SUBJECT` varchar(255) DEFAULT NULL,
  `HTML` text,
  PRIMARY KEY (`ID_TEMPLATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "mailtemplate"
#

INSERT INTO `mailtemplate` VALUES (1,'REGISTRO DE USUARIO','Gracias por registrate en nuestra página, Solo a Dios la Gloria.','<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n<head>\r\n<title></title>\r\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n<style type=\"text/css\">\r\n  a{\r\n   color:#ffffff; \r\n  }\r\n</style>\r\n</head>\r\n<body>\r\n<div style=\"width:100%;\" align=\"center\">\r\n<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n  <tr>\r\n        <br>\r\n        <br>\r\n        <table width=\"583\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n          <tr>\r\n            <td align=\"left\" valign=\"top\"><img src=\"http://imageshack.com/a/img910/9964/oa9XQ1.jpg\" width=\"583\" height=\"118\" style=\"border-radius: 5px\"></td>\r\n          </tr>\r\n          <tr>\r\n            <td align=\"left\" valign=\"top\" bgcolor=\"#FFFFFF\" style=\"background-color:#FFFFFF;\">\r\n              <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n                  <tr>\r\n                    <td align=\"left\" valign=\"top\">\r\n                      <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n                          <tr>\r\n                            <td align=\"center\" valign=\"top\">\r\n                              <div style=\"text-align:center;\"><img style=\"height: 200px; width: 200px;\" src=\"http://upload.wikimedia.org/wikipedia/commons/f/f7/PRESBITERIANA_1.jpg\"></div>\r\n                            \t<div style=\"color:#245da5; font-family:Times New Roman, Times, serif; font-size:25px;\">Himnario Presbiteriano de M&eacute;xico.</div>\r\n                            </td>\r\n                          </tr>\r\n                        \r\n                          <tr>\r\n                            <td align=\"left\" valign=\"top\" style=\"font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#525252;\">\r\n                              <br>\r\n                              <div><p style=\"text-align:justify\">Hola <b><u>Gustavo Trejo</u></b>, te damos la bienvenida al Himnario Presbiteriano en L&iacute;nea \"S&oacute;lo a Dios la Gloria\", gracias por registrarte.</p></div>\r\n                              <br><p style=\"text-align:justify\">\"La Iglesia Nacional Presbiteriana de M&eacute;xico, es una organizaci&oacute;n cristiana presbiteriana que agrupa a m&aacute;s de 6,000 iglesias y cuenta con 437,690 miembros seg&uacute;n el Censo de Poblaci&oacute;n y Vivienda 2010 en M&eacute;xico. Puede considerarse la denominaci&oacute;n presbiteriana m&aacute;s grande del pa&iacute;s y como la segunda denominaci&oacute;n protestante m&aacute;s grande despu&eacute;s de las Asambleas de Dios.\"</p><br>\r\n                            </td>\r\n                        </tr>\r\n                        <tr>\r\n                        <td height=\"50\" valign=\"middle\" style=\"background-color:#3770b7;color:#FFFFFF; border-radius: 5px;font-size:11px; font-family:Arial, Helvetica, sans-serif;text-align:center\">\r\n                            <b>Cont&aacute;ctanos por correo  electr&oacute;nico: </b> godweb.mx@gmail.com.  <b> <p>Web desarrollada por:</b> \r\n                            <a href=\"https://mx.linkedin.com/pub/gustavo-ulises-trejo-armenta/94/aa6/262\">Ulysses M@c</a> </p>\r\n                        </td>\r\n                      </tr>\r\n                      </table>\r\n                    </td>\r\n                  </tr>\r\n              </table>\r\n               </td>\r\n            </tr>\r\n        </table>\r\n        <br>\r\n        <br>\r\n     </tr>\r\n</table>\r\n</div>\r\n</body>\r\n</html>');
