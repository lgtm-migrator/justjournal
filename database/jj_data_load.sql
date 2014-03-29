-- phpMyAdmin SQL Dump
-- version 4.0.10
-- http://www.phpmyadmin.net
--
-- Host: db.midnightbsd.org
-- Generation Time: Mar 24, 2014 at 12:19 PM
-- Server version: 5.6.14
-- PHP Version: 5.4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jj`
--

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`id`, `title`, `iso`, `iso3`, `numcode`, `iso_title`) VALUES
  (1, 'United States', 'US', 'USA', 840, 'UNITED STATES'),
  (3, 'United Kingdom', 'GB', 'GBR', 826, 'UNITED KINGDOM'),
  (962, 'Afghanistan', 'AF', 'AFG', 4, 'AFGHANISTAN'),
  (963, 'Albania', 'AL', 'ALB', 8, 'ALBANIA'),
  (964, 'Algeria', 'DZ', 'DZA', 12, 'ALGERIA'),
  (965, 'American Samoa', 'AS', 'ASM', 16, 'AMERICAN SAMOA'),
  (966, 'Andorra', 'AD', 'AND', 20, 'ANDORRA'),
  (967, 'Angola', 'AO', 'AGO', 24, 'ANGOLA'),
  (968, 'Anguilla', 'AI', 'AIA', 660, 'ANGUILLA'),
  (969, 'Antarctica', 'AQ', NULL, NULL, 'ANTARCTICA'),
  (970, 'Antigua and Barbuda', 'AG', 'ATG', 28, 'ANTIGUA AND BARBUDA'),
  (971, 'Argentina', 'AR', 'ARG', 32, 'ARGENTINA'),
  (972, 'Armenia', 'AM', 'ARM', 51, 'ARMENIA'),
  (973, 'Aruba', 'AW', 'ABW', 533, 'ARUBA'),
  (974, 'Australia', 'AU', 'AUS', 36, 'AUSTRALIA'),
  (975, 'Austria', 'AT', 'AUT', 40, 'AUSTRIA'),
  (976, 'Azerbaijan', 'AZ', 'AZE', 31, 'AZERBAIJAN'),
  (977, 'Bahamas', 'BS', 'BHS', 44, 'BAHAMAS'),
  (978, 'Bahrain', 'BH', 'BHR', 48, 'BAHRAIN'),
  (979, 'Bangladesh', 'BD', 'BGD', 50, 'BANGLADESH'),
  (980, 'Barbados', 'BB', 'BRB', 52, 'BARBADOS'),
  (981, 'Belarus', 'BY', 'BLR', 112, 'BELARUS'),
  (982, 'Belgium', 'BE', 'BEL', 56, 'BELGIUM'),
  (983, 'Belize', 'BZ', 'BLZ', 84, 'BELIZE'),
  (984, 'Benin', 'BJ', 'BEN', 204, 'BENIN'),
  (985, 'Bermuda', 'BM', 'BMU', 60, 'BERMUDA'),
  (986, 'Bhutan', 'BT', 'BTN', 64, 'BHUTAN'),
  (987, 'Bolivia', 'BO', 'BOL', 68, 'BOLIVIA'),
  (988, 'Bosnia and Herzegovina', 'BA', 'BIH', 70, 'BOSNIA AND HERZEGOVINA'),
  (989, 'Botswana', 'BW', 'BWA', 72, 'BOTSWANA'),
  (990, 'Bouvet Island', 'BV', NULL, NULL, 'BOUVET ISLAND'),
  (991, 'Brazil', 'BR', 'BRA', 76, 'BRAZIL'),
  (992, 'British Indian Ocean Territory', 'IO', NULL, NULL, 'BRITISH INDIAN OCEAN TERRITORY'),
  (993, 'Brunei Darussalam', 'BN', 'BRN', 96, 'BRUNEI DARUSSALAM'),
  (994, 'Bulgaria', 'BG', 'BGR', 100, 'BULGARIA'),
  (995, 'Burkina Faso', 'BF', 'BFA', 854, 'BURKINA FASO'),
  (996, 'Burundi', 'BI', 'BDI', 108, 'BURUNDI'),
  (997, 'Cambodia', 'KH', 'KHM', 116, 'CAMBODIA'),
  (998, 'Cameroon', 'CM', 'CMR', 120, 'CAMEROON'),
  (999, 'Canada', 'CA', 'CAN', 124, 'CANADA'),
  (1000, 'Cape Verde', 'CV', 'CPV', 132, 'CAPE VERDE'),
  (1001, 'Cayman Islands', 'KY', 'CYM', 136, 'CAYMAN ISLANDS'),
  (1002, 'Central African Republic', 'CF', 'CAF', 140, 'CENTRAL AFRICAN REPUBLIC'),
  (1003, 'Chad', 'TD', 'TCD', 148, 'CHAD'),
  (1004, 'Chile', 'CL', 'CHL', 152, 'CHILE'),
  (1005, 'China', 'CN', 'CHN', 156, 'CHINA'),
  (1006, 'Christmas Island', 'CX', NULL, NULL, 'CHRISTMAS ISLAND'),
  (1007, 'Cocos (Keeling) Islands', 'CC', NULL, NULL, 'COCOS (KEELING) ISLANDS'),
  (1008, 'Colombia', 'CO', 'COL', 170, 'COLOMBIA'),
  (1009, 'Comoros', 'KM', 'COM', 174, 'COMOROS'),
  (1010, 'Congo', 'CG', 'COG', 178, 'CONGO'),
  (1011, 'Congo, the Democratic Republic of the', 'CD', 'COD', 180, 'CONGO, THE DEMOCRATIC REPUBLIC OF THE'),
  (1012, 'Cook Islands', 'CK', 'COK', 184, 'COOK ISLANDS'),
  (1013, 'Costa Rica', 'CR', 'CRI', 188, 'COSTA RICA'),
  (1014, 'Cote D''Ivoire', 'CI', 'CIV', 384, 'COTE D''IVOIRE'),
  (1015, 'Croatia', 'HR', 'HRV', 191, 'CROATIA'),
  (1016, 'Cuba', 'CU', 'CUB', 192, 'CUBA'),
  (1017, 'Cyprus', 'CY', 'CYP', 196, 'CYPRUS'),
  (1018, 'Czech Republic', 'CZ', 'CZE', 203, 'CZECH REPUBLIC'),
  (1019, 'Denmark', 'DK', 'DNK', 208, 'DENMARK'),
  (1020, 'Djibouti', 'DJ', 'DJI', 262, 'DJIBOUTI'),
  (1021, 'Dominica', 'DM', 'DMA', 212, 'DOMINICA'),
  (1022, 'Dominican Republic', 'DO', 'DOM', 214, 'DOMINICAN REPUBLIC'),
  (1023, 'Ecuador', 'EC', 'ECU', 218, 'ECUADOR'),
  (1024, 'Egypt', 'EG', 'EGY', 818, 'EGYPT'),
  (1025, 'El Salvador', 'SV', 'SLV', 222, 'EL SALVADOR'),
  (1026, 'Equatorial Guinea', 'GQ', 'GNQ', 226, 'EQUATORIAL GUINEA'),
  (1027, 'Eritrea', 'ER', 'ERI', 232, 'ERITREA'),
  (1028, 'Estonia', 'EE', 'EST', 233, 'ESTONIA'),
  (1029, 'Ethiopia', 'ET', 'ETH', 231, 'ETHIOPIA'),
  (1030, 'Falkland Islands (Malvinas)', 'FK', 'FLK', 238, 'FALKLAND ISLANDS (MALVINAS)'),
  (1031, 'Faroe Islands', 'FO', 'FRO', 234, 'FAROE ISLANDS'),
  (1032, 'Fiji', 'FJ', 'FJI', 242, 'FIJI'),
  (1033, 'Finland', 'FI', 'FIN', 246, 'FINLAND'),
  (1034, 'France', 'FR', 'FRA', 250, 'FRANCE'),
  (1035, 'French Guiana', 'GF', 'GUF', 254, 'FRENCH GUIANA'),
  (1036, 'French Polynesia', 'PF', 'PYF', 258, 'FRENCH POLYNESIA'),
  (1037, 'French Southern Territories', 'TF', NULL, NULL, 'FRENCH SOUTHERN TERRITORIES'),
  (1038, 'Gabon', 'GA', 'GAB', 266, 'GABON'),
  (1039, 'Gambia', 'GM', 'GMB', 270, 'GAMBIA'),
  (1040, 'Georgia', 'GE', 'GEO', 268, 'GEORGIA'),
  (1041, 'Germany', 'DE', 'DEU', 276, 'GERMANY'),
  (1042, 'Ghana', 'GH', 'GHA', 288, 'GHANA'),
  (1043, 'Gibraltar', 'GI', 'GIB', 292, 'GIBRALTAR'),
  (1044, 'Greece', 'GR', 'GRC', 300, 'GREECE'),
  (1045, 'Greenland', 'GL', 'GRL', 304, 'GREENLAND'),
  (1046, 'Grenada', 'GD', 'GRD', 308, 'GRENADA'),
  (1047, 'Guadeloupe', 'GP', 'GLP', 312, 'GUADELOUPE'),
  (1048, 'Guam', 'GU', 'GUM', 316, 'GUAM'),
  (1049, 'Guatemala', 'GT', 'GTM', 320, 'GUATEMALA'),
  (1050, 'Guinea', 'GN', 'GIN', 324, 'GUINEA'),
  (1051, 'Guinea-Bissau', 'GW', 'GNB', 624, 'GUINEA-BISSAU'),
  (1052, 'Guyana', 'GY', 'GUY', 328, 'GUYANA'),
  (1053, 'Haiti', 'HT', 'HTI', 332, 'HAITI'),
  (1054, 'Heard Island and Mcdonald Islands', 'HM', NULL, NULL, 'HEARD ISLAND AND MCDONALD ISLANDS'),
  (1055, 'Holy See (Vatican City State)', 'VA', 'VAT', 336, 'HOLY SEE (VATICAN CITY STATE)'),
  (1056, 'Honduras', 'HN', 'HND', 340, 'HONDURAS'),
  (1057, 'Hong Kong', 'HK', 'HKG', 344, 'HONG KONG'),
  (1058, 'Hungary', 'HU', 'HUN', 348, 'HUNGARY'),
  (1059, 'Iceland', 'IS', 'ISL', 352, 'ICELAND'),
  (1060, 'India', 'IN', 'IND', 356, 'INDIA'),
  (1061, 'Indonesia', 'ID', 'IDN', 360, 'INDONESIA'),
  (1062, 'Iran, Islamic Republic of', 'IR', 'IRN', 364, 'IRAN, ISLAMIC REPUBLIC OF'),
  (1063, 'Iraq', 'IQ', 'IRQ', 368, 'IRAQ'),
  (1064, 'Ireland', 'IE', 'IRL', 372, 'IRELAND'),
  (1065, 'Israel', 'IL', 'ISR', 376, 'ISRAEL'),
  (1066, 'Italy', 'IT', 'ITA', 380, 'ITALY'),
  (1067, 'Jamaica', 'JM', 'JAM', 388, 'JAMAICA'),
  (1068, 'Japan', 'JP', 'JPN', 392, 'JAPAN'),
  (1069, 'Jordan', 'JO', 'JOR', 400, 'JORDAN'),
  (1070, 'Kazakhstan', 'KZ', 'KAZ', 398, 'KAZAKHSTAN'),
  (1071, 'Kenya', 'KE', 'KEN', 404, 'KENYA'),
  (1072, 'Kiribati', 'KI', 'KIR', 296, 'KIRIBATI'),
  (1073, 'Korea, Democratic People''s Republic of', 'KP', 'PRK', 408, 'KOREA, DEMOCRATIC PEOPLE''S REPUBLIC OF'),
  (1074, 'Korea, Republic of', 'KR', 'KOR', 410, 'KOREA, REPUBLIC OF'),
  (1075, 'Kuwait', 'KW', 'KWT', 414, 'KUWAIT'),
  (1076, 'Kyrgyzstan', 'KG', 'KGZ', 417, 'KYRGYZSTAN'),
  (1077, 'Lao People''s Democratic Republic', 'LA', 'LAO', 418, 'LAO PEOPLE''S DEMOCRATIC REPUBLIC'),
  (1078, 'Latvia', 'LV', 'LVA', 428, 'LATVIA'),
  (1079, 'Lebanon', 'LB', 'LBN', 422, 'LEBANON'),
  (1080, 'Lesotho', 'LS', 'LSO', 426, 'LESOTHO'),
  (1081, 'Liberia', 'LR', 'LBR', 430, 'LIBERIA'),
  (1082, 'Libyan Arab Jamahiriya', 'LY', 'LBY', 434, 'LIBYAN ARAB JAMAHIRIYA'),
  (1083, 'Liechtenstein', 'LI', 'LIE', 438, 'LIECHTENSTEIN'),
  (1084, 'Lithuania', 'LT', 'LTU', 440, 'LITHUANIA'),
  (1085, 'Luxembourg', 'LU', 'LUX', 442, 'LUXEMBOURG'),
  (1086, 'Macao', 'MO', 'MAC', 446, 'MACAO'),
  (1087, 'Macedonia, the Former Yugoslav Republic of', 'MK', 'MKD', 807, 'MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF'),
  (1088, 'Madagascar', 'MG', 'MDG', 450, 'MADAGASCAR'),
  (1089, 'Malawi', 'MW', 'MWI', 454, 'MALAWI'),
  (1090, 'Malaysia', 'MY', 'MYS', 458, 'MALAYSIA'),
  (1091, 'Maldives', 'MV', 'MDV', 462, 'MALDIVES'),
  (1092, 'Mali', 'ML', 'MLI', 466, 'MALI'),
  (1093, 'Malta', 'MT', 'MLT', 470, 'MALTA'),
  (1094, 'Marshall Islands', 'MH', 'MHL', 584, 'MARSHALL ISLANDS'),
  (1095, 'Martinique', 'MQ', 'MTQ', 474, 'MARTINIQUE'),
  (1096, 'Mauritania', 'MR', 'MRT', 478, 'MAURITANIA'),
  (1097, 'Mauritius', 'MU', 'MUS', 480, 'MAURITIUS'),
  (1098, 'Mayotte', 'YT', NULL, NULL, 'MAYOTTE'),
  (1099, 'Mexico', 'MX', 'MEX', 484, 'MEXICO'),
  (1100, 'Micronesia, Federated States of', 'FM', 'FSM', 583, 'MICRONESIA, FEDERATED STATES OF'),
  (1101, 'Moldova, Republic of', 'MD', 'MDA', 498, 'MOLDOVA, REPUBLIC OF'),
  (1102, 'Monaco', 'MC', 'MCO', 492, 'MONACO'),
  (1103, 'Mongolia', 'MN', 'MNG', 496, 'MONGOLIA'),
  (1104, 'Montserrat', 'MS', 'MSR', 500, 'MONTSERRAT'),
  (1105, 'Morocco', 'MA', 'MAR', 504, 'MOROCCO'),
  (1106, 'Mozambique', 'MZ', 'MOZ', 508, 'MOZAMBIQUE'),
  (1107, 'Myanmar', 'MM', 'MMR', 104, 'MYANMAR'),
  (1108, 'Namibia', 'NA', 'NAM', 516, 'NAMIBIA'),
  (1109, 'Nauru', 'NR', 'NRU', 520, 'NAURU'),
  (1110, 'Nepal', 'NP', 'NPL', 524, 'NEPAL'),
  (1111, 'Netherlands', 'NL', 'NLD', 528, 'NETHERLANDS'),
  (1112, 'Netherlands Antilles', 'AN', 'ANT', 530, 'NETHERLANDS ANTILLES'),
  (1113, 'New Caledonia', 'NC', 'NCL', 540, 'NEW CALEDONIA'),
  (1114, 'New Zealand', 'NZ', 'NZL', 554, 'NEW ZEALAND'),
  (1115, 'Nicaragua', 'NI', 'NIC', 558, 'NICARAGUA'),
  (1116, 'Niger', 'NE', 'NER', 562, 'NIGER'),
  (1117, 'Nigeria', 'NG', 'NGA', 566, 'NIGERIA'),
  (1118, 'Niue', 'NU', 'NIU', 570, 'NIUE'),
  (1119, 'Norfolk Island', 'NF', 'NFK', 574, 'NORFOLK ISLAND'),
  (1120, 'Northern Mariana Islands', 'MP', 'MNP', 580, 'NORTHERN MARIANA ISLANDS'),
  (1121, 'Norway', 'NO', 'NOR', 578, 'NORWAY'),
  (1122, 'Oman', 'OM', 'OMN', 512, 'OMAN'),
  (1123, 'Pakistan', 'PK', 'PAK', 586, 'PAKISTAN'),
  (1124, 'Palau', 'PW', 'PLW', 585, 'PALAU'),
  (1125, 'Palestinian Territory, Occupied', 'PS', NULL, NULL, 'PALESTINIAN TERRITORY, OCCUPIED'),
  (1126, 'Panama', 'PA', 'PAN', 591, 'PANAMA'),
  (1127, 'Papua New Guinea', 'PG', 'PNG', 598, 'PAPUA NEW GUINEA'),
  (1128, 'Paraguay', 'PY', 'PRY', 600, 'PARAGUAY'),
  (1129, 'Peru', 'PE', 'PER', 604, 'PERU'),
  (1130, 'Philippines', 'PH', 'PHL', 608, 'PHILIPPINES'),
  (1131, 'Pitcairn', 'PN', 'PCN', 612, 'PITCAIRN'),
  (1132, 'Poland', 'PL', 'POL', 616, 'POLAND'),
  (1133, 'Portugal', 'PT', 'PRT', 620, 'PORTUGAL'),
  (1134, 'Puerto Rico', 'PR', 'PRI', 630, 'PUERTO RICO'),
  (1135, 'Qatar', 'QA', 'QAT', 634, 'QATAR'),
  (1136, 'Reunion', 'RE', 'REU', 638, 'REUNION'),
  (1137, 'Romania', 'RO', 'ROM', 642, 'ROMANIA'),
  (1138, 'Russian Federation', 'RU', 'RUS', 643, 'RUSSIAN FEDERATION'),
  (1139, 'Rwanda', 'RW', 'RWA', 646, 'RWANDA'),
  (1140, 'Saint Helena', 'SH', 'SHN', 654, 'SAINT HELENA'),
  (1141, 'Saint Kitts and Nevis', 'KN', 'KNA', 659, 'SAINT KITTS AND NEVIS'),
  (1142, 'Saint Lucia', 'LC', 'LCA', 662, 'SAINT LUCIA'),
  (1143, 'Saint Pierre and Miquelon', 'PM', 'SPM', 666, 'SAINT PIERRE AND MIQUELON'),
  (1144, 'Saint Vincent and the Grenadines', 'VC', 'VCT', 670, 'SAINT VINCENT AND THE GRENADINES'),
  (1145, 'Samoa', 'WS', 'WSM', 882, 'SAMOA'),
  (1146, 'San Marino', 'SM', 'SMR', 674, 'SAN MARINO'),
  (1147, 'Sao Tome and Principe', 'ST', 'STP', 678, 'SAO TOME AND PRINCIPE'),
  (1148, 'Saudi Arabia', 'SA', 'SAU', 682, 'SAUDI ARABIA'),
  (1149, 'Senegal', 'SN', 'SEN', 686, 'SENEGAL'),
  (1150, 'Serbia and Montenegro', 'CS', NULL, NULL, 'SERBIA AND MONTENEGRO'),
  (1151, 'Seychelles', 'SC', 'SYC', 690, 'SEYCHELLES'),
  (1152, 'Sierra Leone', 'SL', 'SLE', 694, 'SIERRA LEONE'),
  (1153, 'Singapore', 'SG', 'SGP', 702, 'SINGAPORE'),
  (1154, 'Slovakia', 'SK', 'SVK', 703, 'SLOVAKIA'),
  (1155, 'Slovenia', 'SI', 'SVN', 705, 'SLOVENIA'),
  (1156, 'Solomon Islands', 'SB', 'SLB', 90, 'SOLOMON ISLANDS'),
  (1157, 'Somalia', 'SO', 'SOM', 706, 'SOMALIA'),
  (1158, 'South Africa', 'ZA', 'ZAF', 710, 'SOUTH AFRICA'),
  (1159, 'South Georgia and the South Sandwich Islands', 'GS', NULL, NULL, 'SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS'),
  (1160, 'Spain', 'ES', 'ESP', 724, 'SPAIN'),
  (1161, 'Sri Lanka', 'LK', 'LKA', 144, 'SRI LANKA'),
  (1162, 'Sudan', 'SD', 'SDN', 736, 'SUDAN'),
  (1163, 'Suriname', 'SR', 'SUR', 740, 'SURINAME'),
  (1164, 'Svalbard and Jan Mayen', 'SJ', 'SJM', 744, 'SVALBARD AND JAN MAYEN'),
  (1165, 'Swaziland', 'SZ', 'SWZ', 748, 'SWAZILAND'),
  (1166, 'Sweden', 'SE', 'SWE', 752, 'SWEDEN'),
  (1167, 'Switzerland', 'CH', 'CHE', 756, 'SWITZERLAND'),
  (1168, 'Syrian Arab Republic', 'SY', 'SYR', 760, 'SYRIAN ARAB REPUBLIC'),
  (1169, 'Taiwan, Province of China', 'TW', 'TWN', 158, 'TAIWAN, PROVINCE OF CHINA'),
  (1170, 'Tajikistan', 'TJ', 'TJK', 762, 'TAJIKISTAN'),
  (1171, 'Tanzania, United Republic of', 'TZ', 'TZA', 834, 'TANZANIA, UNITED REPUBLIC OF'),
  (1172, 'Thailand', 'TH', 'THA', 764, 'THAILAND'),
  (1173, 'Timor-Leste', 'TL', NULL, NULL, 'TIMOR-LESTE'),
  (1174, 'Togo', 'TG', 'TGO', 768, 'TOGO'),
  (1175, 'Tokelau', 'TK', 'TKL', 772, 'TOKELAU'),
  (1176, 'Tonga', 'TO', 'TON', 776, 'TONGA'),
  (1177, 'Trinidad and Tobago', 'TT', 'TTO', 780, 'TRINIDAD AND TOBAGO'),
  (1178, 'Tunisia', 'TN', 'TUN', 788, 'TUNISIA'),
  (1179, 'Turkey', 'TR', 'TUR', 792, 'TURKEY'),
  (1180, 'Turkmenistan', 'TM', 'TKM', 795, 'TURKMENISTAN'),
  (1181, 'Turks and Caicos Islands', 'TC', 'TCA', 796, 'TURKS AND CAICOS ISLANDS'),
  (1182, 'Tuvalu', 'TV', 'TUV', 798, 'TUVALU'),
  (1183, 'Uganda', 'UG', 'UGA', 800, 'UGANDA'),
  (1184, 'Ukraine', 'UA', 'UKR', 804, 'UKRAINE'),
  (1185, 'United Arab Emirates', 'AE', 'ARE', 784, 'UNITED ARAB EMIRATES'),
  (1186, 'United States Minor Outlying Islands', 'UM', NULL, NULL, 'UNITED STATES MINOR OUTLYING ISLANDS'),
  (1187, 'Uruguay', 'UY', 'URY', 858, 'URUGUAY'),
  (1188, 'Uzbekistan', 'UZ', 'UZB', 860, 'UZBEKISTAN'),
  (1189, 'Vanuatu', 'VU', 'VUT', 548, 'VANUATU'),
  (1190, 'Venezuela', 'VE', 'VEN', 862, 'VENEZUELA'),
  (1191, 'Viet Nam', 'VN', 'VNM', 704, 'VIET NAM'),
  (1192, 'Virgin Islands, British', 'VG', 'VGB', 92, 'VIRGIN ISLANDS, BRITISH'),
  (1193, 'Virgin Islands, U.s.', 'VI', 'VIR', 850, 'VIRGIN ISLANDS, U.S.'),
  (1194, 'Wallis and Futuna', 'WF', 'WLF', 876, 'WALLIS AND FUTUNA'),
  (1195, 'Western Sahara', 'EH', 'ESH', 732, 'WESTERN SAHARA'),
  (1196, 'Yemen', 'YE', 'YEM', 887, 'YEMEN'),
  (1197, 'Zambia', 'ZM', 'ZMB', 894, 'ZAMBIA'),
  (1198, 'Zimbabwe', 'ZW', 'ZWE', 716, 'ZIMBABWE');

--
-- Dumping data for table `entry_security`
--

INSERT INTO `entry_security` (`id`, `title`) VALUES
  (0, 'private'),
  (1, 'friends'),
  (2, 'public');

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`id`, `title`) VALUES
  (0, 'Not Specified'),
  (1, 'Home'),
  (2, 'Work'),
  (3, 'School'),
  (5, 'Other');

--
-- Dumping data for table `mood`
--

INSERT INTO `mood` (`id`, `parentmood`, `title`) VALUES
  (1, 0, 'Happy'),
  (2, 0, 'Sad'),
  (3, 0, 'Awake'),
  (4, 0, 'Tired'),
  (5, 0, 'Angry'),
  (6, 5, 'Mad'),
  (7, 1, 'Gay'),
  (8, 0, 'Silly'),
  (9, 0, 'Confused'),
  (10, 0, 'Bewildered'),
  (11, 0, 'Smart'),
  (12, 0, 'Not Specified'),
  (13, 0, 'Hungry'),
  (14, 0, 'Accomplished'),
  (15, 1, 'Amused'),
  (16, 0, 'Annoyed'),
  (17, 0, 'Anxious'),
  (18, 0, 'Bored'),
  (19, 0, 'Accepted'),
  (20, 0, 'Alone'),
  (21, 0, 'Ashamed'),
  (22, 0, 'Bittersweet'),
  (23, 0, 'Blissful'),
  (24, 0, 'Dark'),
  (25, 5, 'Aggravated'),
  (26, 5, 'Bitchy'),
  (27, 5, 'Cranky'),
  (28, 5, 'Cynical'),
  (29, 5, 'Enraged'),
  (30, 5, 'Frustrated'),
  (31, 5, 'Grumpy'),
  (32, 5, 'Infuriated'),
  (33, 5, 'Irate'),
  (34, 5, 'Irritated'),
  (35, 5, 'Moody'),
  (36, 5, 'Pissed off'),
  (37, 5, 'Stressed'),
  (38, 37, 'Rushed'),
  (39, 9, 'Curious'),
  (40, 0, 'Determined'),
  (41, 40, 'Predatory'),
  (42, 0, 'Devious'),
  (43, 0, 'Energetic'),
  (44, 43, 'Bouncy'),
  (45, 43, 'Hyper'),
  (46, 0, 'Enthralled'),
  (47, 1, 'Cheerful'),
  (48, 1, 'Horny'),
  (49, 1, 'Chipper'),
  (50, 1, 'High'),
  (51, 1, 'Ecstatic'),
  (52, 1, 'Excited'),
  (53, 1, 'Good'),
  (54, 1, 'Grateful'),
  (55, 1, 'Impressed'),
  (56, 1, 'Jubilant'),
  (57, 1, 'Loved'),
  (58, 1, 'Optimistic'),
  (59, 58, 'Hopeful'),
  (60, 1, 'Pleased'),
  (61, 1, 'Refreshed'),
  (62, 61, 'Rejuvenated'),
  (63, 1, 'Relaxed'),
  (64, 63, 'Calm'),
  (65, 63, 'Mellow'),
  (66, 63, 'Peaceful'),
  (67, 63, 'Recumbent'),
  (68, 63, 'Satisfied'),
  (69, 68, 'Content'),
  (70, 69, 'Complacent'),
  (71, 69, 'Indifferent'),
  (72, 68, 'Full'),
  (73, 68, 'Relieved'),
  (74, 1, 'Thankful'),
  (75, 1, 'Touched'),
  (76, 1, 'Surprised'),
  (77, 76, 'Shocked'),
  (78, 8, 'Crazy'),
  (79, 8, 'Ditzy'),
  (80, 8, 'Flirty'),
  (81, 8, 'Giddy'),
  (82, 8, 'Giggly'),
  (83, 8, 'Mischievous'),
  (84, 83, 'Naughty'),
  (85, 8, 'Quixotic'),
  (86, 8, 'Weird'),
  (87, 0, 'Indescribable'),
  (88, 0, 'Nerdy'),
  (89, 88, 'Dorky'),
  (90, 88, 'Geeky'),
  (91, 0, 'Okay'),
  (92, 91, 'Blah'),
  (93, 91, 'Lazy'),
  (94, 93, 'Lethargic'),
  (95, 93, 'Listless'),
  (96, 93, 'Exanimate'),
  (97, 96, 'Apathetic'),
  (98, 96, 'Blank'),
  (99, 2, 'Crappy'),
  (100, 2, 'Crushed'),
  (101, 2, 'Depressed'),
  (102, 2, 'Disappointed'),
  (103, 2, 'Discontent'),
  (104, 2, 'Envious'),
  (105, 2, 'Gloomy'),
  (106, 2, 'Pessimistic'),
  (107, 2, 'Jealous'),
  (108, 2, 'Lonely'),
  (109, 2, 'Melancholy'),
  (110, 2, 'Morose'),
  (111, 2, 'Numb'),
  (112, 2, 'Rejected'),
  (113, 2, 'Sympathetic'),
  (114, 2, 'Uncomfortable'),
  (115, 2, 'Cold'),
  (116, 2, 'Dirty'),
  (117, 2, 'Drunk'),
  (118, 2, 'Exhausted'),
  (119, 2, 'Drained'),
  (120, 2, 'Groggy'),
  (121, 2, 'Sleepy'),
  (122, 2, 'Guilty'),
  (123, 2, 'Hot'),
  (124, 2, 'Restless'),
  (125, 2, 'Sick');

--
-- Dumping data for table `mood_themes`
--

INSERT INTO `mood_themes` (`id`, `owner`, `name`, `des`, `is_public`) VALUES
  (1, 0, 'Default', NULL, 'Y');

--
-- Dumping data for table `mood_theme_data`
--

INSERT INTO `mood_theme_data` (`id`, `moodthemeid`, `moodid`, `picurl`, `width`, `height`) VALUES
  (1, 1, 1, 'smile.gif', 15, 15),
  (2, 1, 2, 'sad.gif', 15, 15),
  (3, 1, 4, 'tired.gif', 15, 15),
  (4, 1, 5, 'angry.gif', 15, 15),
  (5, 1, 9, 'confused.gif', 15, 15),
  (6, 1, 42, 'devious.gif', 15, 15),
  (7, 1, 43, 'energetic.gif', 15, 15);

--
-- Dumping data for table `settings`
--

INSERT INTO `settings` (`name`, `value`, `id`) VALUES
  ('baseuri', 'http://www.justjournal.com/', 1),
  ('commentEnable', 'true', 2),
  ('commentEnableAnonymous', 'true', 3),
  ('commentMailEnable', 'true', 4),
  ('contextPath', '/', 5),
  ('fsPath', '/jj/', 6),
  ('mailEnable', 'true', 7),
  ('mailFrom', 'donotreply@justjournal.com', 8),
  ('mailHost', 'localhost', 9),
  ('mailPass', '', 10),
  ('mailPort', '25', 11),
  ('mailSubject', 'JustJournal Notification', 12),
  ('mailUser', 'jj', 13),
  ('siteAdmin', 'Luke', 14),
  ('siteAdminEmail', 'luke@foolishgames.com', 15),
  ('siteBlog', 'jjsite', 16),
  ('siteDirectory', 'true', 17),
  ('siteEnable', 'true', 18),
  ('siteRss', 'true', 19),
  ('siteSearch', 'true', 20),
  ('tzLocalize', 'true', 21),
  ('tzName', 'EST', 22),
  ('tzOffset', '-5', 23),
  ('tzUseGMT', 'false', 24),
  ('userAllowNew', 'true', 25);

--
-- Dumping data for table `style`
--

INSERT INTO `style` (`id`, `title`, `desc`, `modified`) VALUES
  (1, 'Classic Theme', 'Default Justjournal theme in white', '2012-06-20 03:42:57'),
  (2, 'Classic Theme in blue', 'Original JJ theme in blue', '2012-06-20 03:42:57'),
  (3, 'Classic Theme in purple', 'A taste of purple', '2012-06-20 03:42:57'),
  (4, 'Classic Theme in pink', 'very pink!', '2012-06-20 03:42:57'),
  (5, 'Jewel Theme', 'A tribute to Jewel.', '2012-06-20 03:42:57'),
  (6, 'Modern', 'A clean, light theme.', '2012-06-20 03:42:57'),
  (7, 'Modern Purple', 'A very purple theme.', '2012-06-20 03:42:57'),
  (8, 'Modern Girl', 'A pink, red and purple creation.', '2012-06-20 03:42:57'),
  (9, 'American Football', 'Football theme.', '2012-06-20 03:42:57'),
  (10, 'Modern Red', 'Fire engines eat your heart out.', '2012-06-20 03:42:57'),
  (11, 'Deep Blue Ocean', 'Created by Geomaniak', '2012-06-20 03:42:57'),
  (12, 'Gold and Blue', 'A subtle theme based on Modern.', '2012-06-20 03:42:57'),
  (13, 'Modern Black', 'A dark style', '2012-06-20 03:42:57'),
  (14, 'Modern Right Black', 'A reverse layout', '2012-06-20 03:42:57'),
  (15, 'Colors', 'A bright theme', '2012-06-20 03:42:57'),
  (16, 'Ugly Orange', 'Orange', '2012-06-20 03:42:57');

--
-- Dumping data for table `timezones`
--

INSERT INTO `timezones` (`id`, `name`) VALUES
  (1662, 'ACT'),
  (1669, 'AET'),
  (1388, 'Africa/Abidjan'),
  (1389, 'Africa/Accra'),
  (1534, 'Africa/Addis_Ababa'),
  (1436, 'Africa/Algiers'),
  (1535, 'Africa/Asmera'),
  (1390, 'Africa/Bamako'),
  (1437, 'Africa/Bangui'),
  (1391, 'Africa/Banjul'),
  (1392, 'Africa/Bissau'),
  (1488, 'Africa/Blantyre'),
  (1438, 'Africa/Brazzaville'),
  (1489, 'Africa/Bujumbura'),
  (1490, 'Africa/Cairo'),
  (1393, 'Africa/Casablanca'),
  (1439, 'Africa/Ceuta'),
  (1394, 'Africa/Conakry'),
  (1395, 'Africa/Dakar'),
  (1536, 'Africa/Dar_es_Salaam'),
  (1537, 'Africa/Djibouti'),
  (1440, 'Africa/Douala'),
  (1396, 'Africa/El_Aaiun'),
  (1397, 'Africa/Freetown'),
  (1491, 'Africa/Gaborone'),
  (1492, 'Africa/Harare'),
  (1493, 'Africa/Johannesburg'),
  (1538, 'Africa/Kampala'),
  (1539, 'Africa/Khartoum'),
  (1494, 'Africa/Kigali'),
  (1441, 'Africa/Kinshasa'),
  (1442, 'Africa/Lagos'),
  (1443, 'Africa/Libreville'),
  (1398, 'Africa/Lome'),
  (1444, 'Africa/Luanda'),
  (1495, 'Africa/Lubumbashi'),
  (1496, 'Africa/Lusaka'),
  (1445, 'Africa/Malabo'),
  (1497, 'Africa/Maputo'),
  (1498, 'Africa/Maseru'),
  (1499, 'Africa/Mbabane'),
  (1540, 'Africa/Mogadishu'),
  (1399, 'Africa/Monrovia'),
  (1541, 'Africa/Nairobi'),
  (1446, 'Africa/Ndjamena'),
  (1447, 'Africa/Niamey'),
  (1400, 'Africa/Nouakchott'),
  (1401, 'Africa/Ouagadougou'),
  (1448, 'Africa/Porto-Novo'),
  (1402, 'Africa/Sao_Tome'),
  (1403, 'Africa/Timbuktu'),
  (1500, 'Africa/Tripoli'),
  (1449, 'Africa/Tunis'),
  (1450, 'Africa/Windhoek'),
  (1346, 'AGT'),
  (1160, 'America/Adak'),
  (1174, 'America/Anchorage'),
  (1300, 'America/Anguilla'),
  (1301, 'America/Antigua'),
  (1347, 'America/Araguaina'),
  (1348, 'America/Argentina/Buenos_Aires'),
  (1349, 'America/Argentina/Catamarca'),
  (1350, 'America/Argentina/ComodRivadavia'),
  (1351, 'America/Argentina/Cordoba'),
  (1352, 'America/Argentina/Jujuy'),
  (1353, 'America/Argentina/La_Rioja'),
  (1354, 'America/Argentina/Mendoza'),
  (1355, 'America/Argentina/Rio_Gallegos'),
  (1356, 'America/Argentina/San_Juan'),
  (1357, 'America/Argentina/Tucuman'),
  (1358, 'America/Argentina/Ushuaia'),
  (1302, 'America/Aruba'),
  (1303, 'America/Asuncion'),
  (1161, 'America/Atka'),
  (1359, 'America/Bahia'),
  (1304, 'America/Barbados'),
  (1360, 'America/Belem'),
  (1223, 'America/Belize'),
  (1305, 'America/Boa_Vista'),
  (1254, 'America/Bogota'),
  (1200, 'America/Boise'),
  (1361, 'America/Buenos_Aires'),
  (1201, 'America/Cambridge_Bay'),
  (1306, 'America/Campo_Grande'),
  (1224, 'America/Cancun'),
  (1307, 'America/Caracas'),
  (1362, 'America/Catamarca'),
  (1363, 'America/Cayenne'),
  (1255, 'America/Cayman'),
  (1225, 'America/Chicago'),
  (1202, 'America/Chihuahua'),
  (1256, 'America/Coral_Harbour'),
  (1364, 'America/Cordoba'),
  (1226, 'America/Costa_Rica'),
  (1308, 'America/Cuiaba'),
  (1309, 'America/Curacao'),
  (1404, 'America/Danmarkshavn'),
  (1183, 'America/Dawson'),
  (1203, 'America/Dawson_Creek'),
  (1204, 'America/Denver'),
  (1257, 'America/Detroit'),
  (1310, 'America/Dominica'),
  (1205, 'America/Edmonton'),
  (1258, 'America/Eirunepe'),
  (1227, 'America/El_Salvador'),
  (1184, 'America/Ensenada'),
  (1365, 'America/Fortaleza'),
  (1259, 'America/Fort_Wayne'),
  (1311, 'America/Glace_Bay'),
  (1366, 'America/Godthab'),
  (1312, 'America/Goose_Bay'),
  (1260, 'America/Grand_Turk'),
  (1313, 'America/Grenada'),
  (1314, 'America/Guadeloupe'),
  (1228, 'America/Guatemala'),
  (1261, 'America/Guayaquil'),
  (1315, 'America/Guyana'),
  (1316, 'America/Halifax'),
  (1262, 'America/Havana'),
  (1206, 'America/Hermosillo'),
  (1263, 'America/Indiana/Indianapolis'),
  (1264, 'America/Indiana/Knox'),
  (1265, 'America/Indiana/Marengo'),
  (1266, 'America/Indiana/Vevay'),
  (1267, 'America/Indianapolis'),
  (1207, 'America/Inuvik'),
  (1268, 'America/Iqaluit'),
  (1269, 'America/Jamaica'),
  (1367, 'America/Jujuy'),
  (1175, 'America/Juneau'),
  (1270, 'America/Kentucky/Louisville'),
  (1271, 'America/Kentucky/Monticello'),
  (1272, 'America/Knox_IN'),
  (1317, 'America/La_Paz'),
  (1273, 'America/Lima'),
  (1185, 'America/Los_Angeles'),
  (1274, 'America/Louisville'),
  (1368, 'America/Maceio'),
  (1229, 'America/Managua'),
  (1318, 'America/Manaus'),
  (1319, 'America/Martinique'),
  (1208, 'America/Mazatlan'),
  (1369, 'America/Mendoza'),
  (1230, 'America/Menominee'),
  (1231, 'America/Merida'),
  (1232, 'America/Mexico_City'),
  (1370, 'America/Miquelon'),
  (1233, 'America/Monterrey'),
  (1371, 'America/Montevideo'),
  (1275, 'America/Montreal'),
  (1320, 'America/Montserrat'),
  (1276, 'America/Nassau'),
  (1277, 'America/New_York'),
  (1278, 'America/Nipigon'),
  (1176, 'America/Nome'),
  (1380, 'America/Noronha'),
  (1234, 'America/North_Dakota/Center'),
  (1279, 'America/Panama'),
  (1280, 'America/Pangnirtung'),
  (1372, 'America/Paramaribo'),
  (1209, 'America/Phoenix'),
  (1281, 'America/Port-au-Prince'),
  (1282, 'America/Porto_Acre'),
  (1322, 'America/Porto_Velho'),
  (1321, 'America/Port_of_Spain'),
  (1323, 'America/Puerto_Rico'),
  (1235, 'America/Rainy_River'),
  (1236, 'America/Rankin_Inlet'),
  (1373, 'America/Recife'),
  (1237, 'America/Regina'),
  (1283, 'America/Rio_Branco'),
  (1374, 'America/Rosario'),
  (1324, 'America/Santiago'),
  (1325, 'America/Santo_Domingo'),
  (1375, 'America/Sao_Paulo'),
  (1384, 'America/Scoresbysund'),
  (1210, 'America/Shiprock'),
  (1343, 'America/St_Johns'),
  (1326, 'America/St_Kitts'),
  (1327, 'America/St_Lucia'),
  (1328, 'America/St_Thomas'),
  (1329, 'America/St_Vincent'),
  (1238, 'America/Swift_Current'),
  (1239, 'America/Tegucigalpa'),
  (1330, 'America/Thule'),
  (1284, 'America/Thunder_Bay'),
  (1186, 'America/Tijuana'),
  (1285, 'America/Toronto'),
  (1331, 'America/Tortola'),
  (1187, 'America/Vancouver'),
  (1332, 'America/Virgin'),
  (1188, 'America/Whitehorse'),
  (1240, 'America/Winnipeg'),
  (1177, 'America/Yakutat'),
  (1211, 'America/Yellowknife'),
  (1622, 'Antarctica/Casey'),
  (1610, 'Antarctica/Davis'),
  (1670, 'Antarctica/DumontDUrville'),
  (1593, 'Antarctica/Mawson'),
  (1702, 'Antarctica/McMurdo'),
  (1333, 'Antarctica/Palmer'),
  (1376, 'Antarctica/Rothera'),
  (1703, 'Antarctica/South_Pole'),
  (1542, 'Antarctica/Syowa'),
  (1594, 'Antarctica/Vostok'),
  (1451, 'Arctic/Longyearbyen'),
  (1487, 'ART'),
  (1543, 'Asia/Aden'),
  (1595, 'Asia/Almaty'),
  (1501, 'Asia/Amman'),
  (1704, 'Asia/Anadyr'),
  (1576, 'Asia/Aqtau'),
  (1577, 'Asia/Aqtobe'),
  (1578, 'Asia/Ashgabat'),
  (1579, 'Asia/Ashkhabad'),
  (1544, 'Asia/Baghdad'),
  (1545, 'Asia/Bahrain'),
  (1565, 'Asia/Baku'),
  (1611, 'Asia/Bangkok'),
  (1502, 'Asia/Beirut'),
  (1596, 'Asia/Bishkek'),
  (1623, 'Asia/Brunei'),
  (1590, 'Asia/Calcutta'),
  (1650, 'Asia/Choibalsan'),
  (1624, 'Asia/Chongqing'),
  (1625, 'Asia/Chungking'),
  (1597, 'Asia/Colombo'),
  (1598, 'Asia/Dacca'),
  (1503, 'Asia/Damascus'),
  (1599, 'Asia/Dhaka'),
  (1651, 'Asia/Dili'),
  (1566, 'Asia/Dubai'),
  (1580, 'Asia/Dushanbe'),
  (1504, 'Asia/Gaza'),
  (1626, 'Asia/Harbin'),
  (1627, 'Asia/Hong_Kong'),
  (1612, 'Asia/Hovd'),
  (1628, 'Asia/Irkutsk'),
  (1505, 'Asia/Istanbul'),
  (1613, 'Asia/Jakarta'),
  (1652, 'Asia/Jayapura'),
  (1506, 'Asia/Jerusalem'),
  (1575, 'Asia/Kabul'),
  (1705, 'Asia/Kamchatka'),
  (1581, 'Asia/Karachi'),
  (1629, 'Asia/Kashgar'),
  (1592, 'Asia/Katmandu'),
  (1614, 'Asia/Krasnoyarsk'),
  (1630, 'Asia/Kuala_Lumpur'),
  (1631, 'Asia/Kuching'),
  (1546, 'Asia/Kuwait'),
  (1632, 'Asia/Macao'),
  (1633, 'Asia/Macau'),
  (1693, 'Asia/Magadan'),
  (1634, 'Asia/Makassar'),
  (1635, 'Asia/Manila'),
  (1567, 'Asia/Muscat'),
  (1507, 'Asia/Nicosia'),
  (1600, 'Asia/Novosibirsk'),
  (1601, 'Asia/Omsk'),
  (1582, 'Asia/Oral'),
  (1615, 'Asia/Phnom_Penh'),
  (1616, 'Asia/Pontianak'),
  (1653, 'Asia/Pyongyang'),
  (1547, 'Asia/Qatar'),
  (1602, 'Asia/Qyzylorda'),
  (1608, 'Asia/Rangoon'),
  (1548, 'Asia/Riyadh'),
  (1557, 'Asia/Riyadh87'),
  (1558, 'Asia/Riyadh88'),
  (1559, 'Asia/Riyadh89'),
  (1617, 'Asia/Saigon'),
  (1671, 'Asia/Sakhalin'),
  (1583, 'Asia/Samarkand'),
  (1654, 'Asia/Seoul'),
  (1636, 'Asia/Shanghai'),
  (1637, 'Asia/Singapore'),
  (1638, 'Asia/Taipei'),
  (1584, 'Asia/Tashkent'),
  (1549, 'Asia/Tbilisi'),
  (1563, 'Asia/Tehran'),
  (1508, 'Asia/Tel_Aviv'),
  (1603, 'Asia/Thimbu'),
  (1604, 'Asia/Thimphu'),
  (1655, 'Asia/Tokyo'),
  (1639, 'Asia/Ujung_Pandang'),
  (1640, 'Asia/Ulaanbaatar'),
  (1641, 'Asia/Ulan_Bator'),
  (1642, 'Asia/Urumqi'),
  (1618, 'Asia/Vientiane'),
  (1672, 'Asia/Vladivostok'),
  (1656, 'Asia/Yakutsk'),
  (1585, 'Asia/Yekaterinburg'),
  (1568, 'Asia/Yerevan'),
  (1173, 'AST'),
  (1385, 'Atlantic/Azores'),
  (1334, 'Atlantic/Bermuda'),
  (1405, 'Atlantic/Canary'),
  (1386, 'Atlantic/Cape_Verde'),
  (1406, 'Atlantic/Faeroe'),
  (1452, 'Atlantic/Jan_Mayen'),
  (1407, 'Atlantic/Madeira'),
  (1408, 'Atlantic/Reykjavik'),
  (1381, 'Atlantic/South_Georgia'),
  (1335, 'Atlantic/Stanley'),
  (1409, 'Atlantic/St_Helena'),
  (1673, 'Australia/ACT'),
  (1663, 'Australia/Adelaide'),
  (1674, 'Australia/Brisbane'),
  (1664, 'Australia/Broken_Hill'),
  (1675, 'Australia/Canberra'),
  (1676, 'Australia/Currie'),
  (1665, 'Australia/Darwin'),
  (1677, 'Australia/Hobart'),
  (1691, 'Australia/LHI'),
  (1678, 'Australia/Lindeman'),
  (1692, 'Australia/Lord_Howe'),
  (1679, 'Australia/Melbourne'),
  (1666, 'Australia/North'),
  (1680, 'Australia/NSW'),
  (1643, 'Australia/Perth'),
  (1681, 'Australia/Queensland'),
  (1667, 'Australia/South'),
  (1682, 'Australia/Sydney'),
  (1683, 'Australia/Tasmania'),
  (1684, 'Australia/Victoria'),
  (1644, 'Australia/West'),
  (1668, 'Australia/Yancowinna'),
  (1377, 'BET'),
  (1286, 'Brazil/Acre'),
  (1382, 'Brazil/DeNoronha'),
  (1378, 'Brazil/East'),
  (1336, 'Brazil/West'),
  (1605, 'BST'),
  (1337, 'Canada/Atlantic'),
  (1243, 'Canada/Central'),
  (1244, 'Canada/East-Saskatchewan'),
  (1287, 'Canada/Eastern'),
  (1212, 'Canada/Mountain'),
  (1345, 'Canada/Newfoundland'),
  (1189, 'Canada/Pacific'),
  (1245, 'Canada/Saskatchewan'),
  (1190, 'Canada/Yukon'),
  (1509, 'CAT'),
  (1453, 'CET'),
  (1338, 'Chile/Continental'),
  (1246, 'Chile/EasterIsland'),
  (1344, 'CNT'),
  (1241, 'CST'),
  (1242, 'CST6CDT'),
  (1645, 'CTT'),
  (1288, 'Cuba'),
  (1550, 'EAT'),
  (1454, 'ECT'),
  (1510, 'EET'),
  (1511, 'Egypt'),
  (1410, 'Eire'),
  (1289, 'EST'),
  (1290, 'EST5EDT'),
  (1411, 'Etc/GMT'),
  (1412, 'Etc/GMT+0'),
  (1387, 'Etc/GMT+1'),
  (1162, 'Etc/GMT+10'),
  (1152, 'Etc/GMT+11'),
  (1151, 'Etc/GMT+12'),
  (1383, 'Etc/GMT+2'),
  (1379, 'Etc/GMT+3'),
  (1339, 'Etc/GMT+4'),
  (1291, 'Etc/GMT+5'),
  (1247, 'Etc/GMT+6'),
  (1213, 'Etc/GMT+7'),
  (1191, 'Etc/GMT+8'),
  (1178, 'Etc/GMT+9'),
  (1413, 'Etc/GMT-0'),
  (1455, 'Etc/GMT-1'),
  (1685, 'Etc/GMT-10'),
  (1694, 'Etc/GMT-11'),
  (1706, 'Etc/GMT-12'),
  (1721, 'Etc/GMT-13'),
  (1724, 'Etc/GMT-14'),
  (1512, 'Etc/GMT-2'),
  (1551, 'Etc/GMT-3'),
  (1569, 'Etc/GMT-4'),
  (1586, 'Etc/GMT-5'),
  (1606, 'Etc/GMT-6'),
  (1619, 'Etc/GMT-7'),
  (1646, 'Etc/GMT-8'),
  (1657, 'Etc/GMT-9'),
  (1414, 'Etc/GMT0'),
  (1415, 'Etc/Greenwich'),
  (1416, 'Etc/UCT'),
  (1418, 'Etc/Universal'),
  (1417, 'Etc/UTC'),
  (1419, 'Etc/Zulu'),
  (1456, 'Europe/Amsterdam'),
  (1457, 'Europe/Andorra'),
  (1513, 'Europe/Athens'),
  (1420, 'Europe/Belfast'),
  (1458, 'Europe/Belgrade'),
  (1459, 'Europe/Berlin'),
  (1460, 'Europe/Bratislava'),
  (1461, 'Europe/Brussels'),
  (1514, 'Europe/Bucharest'),
  (1462, 'Europe/Budapest'),
  (1515, 'Europe/Chisinau'),
  (1463, 'Europe/Copenhagen'),
  (1421, 'Europe/Dublin'),
  (1464, 'Europe/Gibraltar'),
  (1516, 'Europe/Helsinki'),
  (1517, 'Europe/Istanbul'),
  (1518, 'Europe/Kaliningrad'),
  (1519, 'Europe/Kiev'),
  (1422, 'Europe/Lisbon'),
  (1465, 'Europe/Ljubljana'),
  (1423, 'Europe/London'),
  (1466, 'Europe/Luxembourg'),
  (1467, 'Europe/Madrid'),
  (1468, 'Europe/Malta'),
  (1520, 'Europe/Mariehamn'),
  (1521, 'Europe/Minsk'),
  (1469, 'Europe/Monaco'),
  (1552, 'Europe/Moscow'),
  (1522, 'Europe/Nicosia'),
  (1470, 'Europe/Oslo'),
  (1471, 'Europe/Paris'),
  (1472, 'Europe/Prague'),
  (1523, 'Europe/Riga'),
  (1473, 'Europe/Rome'),
  (1570, 'Europe/Samara'),
  (1474, 'Europe/San_Marino'),
  (1475, 'Europe/Sarajevo'),
  (1524, 'Europe/Simferopol'),
  (1476, 'Europe/Skopje'),
  (1525, 'Europe/Sofia'),
  (1477, 'Europe/Stockholm'),
  (1526, 'Europe/Tallinn'),
  (1478, 'Europe/Tirane'),
  (1527, 'Europe/Tiraspol'),
  (1528, 'Europe/Uzhgorod'),
  (1479, 'Europe/Vaduz'),
  (1480, 'Europe/Vatican'),
  (1481, 'Europe/Vienna'),
  (1529, 'Europe/Vilnius'),
  (1482, 'Europe/Warsaw'),
  (1483, 'Europe/Zagreb'),
  (1530, 'Europe/Zaporozhye'),
  (1484, 'Europe/Zurich'),
  (1424, 'GB'),
  (1425, 'GB-Eire'),
  (1426, 'GMT'),
  (1427, 'GMT0'),
  (1428, 'Greenwich'),
  (1647, 'Hongkong'),
  (1163, 'HST'),
  (1429, 'Iceland'),
  (1292, 'IET'),
  (1553, 'Indian/Antananarivo'),
  (1607, 'Indian/Chagos'),
  (1620, 'Indian/Christmas'),
  (1609, 'Indian/Cocos'),
  (1554, 'Indian/Comoro'),
  (1587, 'Indian/Kerguelen'),
  (1571, 'Indian/Mahe'),
  (1588, 'Indian/Maldives'),
  (1572, 'Indian/Mauritius'),
  (1555, 'Indian/Mayotte'),
  (1573, 'Indian/Reunion'),
  (1564, 'Iran'),
  (1531, 'Israel'),
  (1591, 'IST'),
  (1293, 'Jamaica'),
  (1659, 'Japan'),
  (1658, 'JST'),
  (1707, 'Kwajalein'),
  (1532, 'Libya'),
  (1485, 'MET'),
  (1192, 'Mexico/BajaNorte'),
  (1216, 'Mexico/BajaSur'),
  (1248, 'Mexico/General'),
  (1560, 'Mideast/Riyadh87'),
  (1561, 'Mideast/Riyadh88'),
  (1562, 'Mideast/Riyadh89'),
  (1153, 'MIT'),
  (1214, 'MST'),
  (1215, 'MST7MDT'),
  (1217, 'Navajo'),
  (1574, 'NET'),
  (1708, 'NST'),
  (1709, 'NZ'),
  (1719, 'NZ-CHAT'),
  (1154, 'Pacific/Apia'),
  (1710, 'Pacific/Auckland'),
  (1720, 'Pacific/Chatham'),
  (1249, 'Pacific/Easter'),
  (1695, 'Pacific/Efate'),
  (1722, 'Pacific/Enderbury'),
  (1164, 'Pacific/Fakaofo'),
  (1711, 'Pacific/Fiji'),
  (1712, 'Pacific/Funafuti'),
  (1250, 'Pacific/Galapagos'),
  (1179, 'Pacific/Gambier'),
  (1696, 'Pacific/Guadalcanal'),
  (1686, 'Pacific/Guam'),
  (1165, 'Pacific/Honolulu'),
  (1166, 'Pacific/Johnston'),
  (1725, 'Pacific/Kiritimati'),
  (1697, 'Pacific/Kosrae'),
  (1713, 'Pacific/Kwajalein'),
  (1714, 'Pacific/Majuro'),
  (1172, 'Pacific/Marquesas'),
  (1155, 'Pacific/Midway'),
  (1715, 'Pacific/Nauru'),
  (1156, 'Pacific/Niue'),
  (1701, 'Pacific/Norfolk'),
  (1698, 'Pacific/Noumea'),
  (1157, 'Pacific/Pago_Pago'),
  (1660, 'Pacific/Palau'),
  (1195, 'Pacific/Pitcairn'),
  (1699, 'Pacific/Ponape'),
  (1687, 'Pacific/Port_Moresby'),
  (1167, 'Pacific/Rarotonga'),
  (1688, 'Pacific/Saipan'),
  (1158, 'Pacific/Samoa'),
  (1168, 'Pacific/Tahiti'),
  (1716, 'Pacific/Tarawa'),
  (1723, 'Pacific/Tongatapu'),
  (1689, 'Pacific/Truk'),
  (1717, 'Pacific/Wake'),
  (1718, 'Pacific/Wallis'),
  (1690, 'Pacific/Yap'),
  (1589, 'PLT'),
  (1218, 'PNT'),
  (1486, 'Poland'),
  (1430, 'Portugal'),
  (1648, 'PRC'),
  (1340, 'PRT'),
  (1193, 'PST'),
  (1194, 'PST8PDT'),
  (1661, 'ROK'),
  (1649, 'Singapore'),
  (1700, 'SST'),
  (1341, 'SystemV/AST4'),
  (1342, 'SystemV/AST4ADT'),
  (1251, 'SystemV/CST6'),
  (1252, 'SystemV/CST6CDT'),
  (1294, 'SystemV/EST5'),
  (1295, 'SystemV/EST5EDT'),
  (1169, 'SystemV/HST10'),
  (1219, 'SystemV/MST7'),
  (1220, 'SystemV/MST7MDT'),
  (1196, 'SystemV/PST8'),
  (1197, 'SystemV/PST8PDT'),
  (1180, 'SystemV/YST9'),
  (1181, 'SystemV/YST9YDT'),
  (1533, 'Turkey'),
  (1431, 'UCT'),
  (1433, 'Universal'),
  (1182, 'US/Alaska'),
  (1170, 'US/Aleutian'),
  (1221, 'US/Arizona'),
  (1253, 'US/Central'),
  (1296, 'US/East-Indiana'),
  (1297, 'US/Eastern'),
  (1171, 'US/Hawaii'),
  (1298, 'US/Indiana-Starke'),
  (1299, 'US/Michigan'),
  (1222, 'US/Mountain'),
  (1198, 'US/Pacific'),
  (1199, 'US/Pacific-New'),
  (1159, 'US/Samoa'),
  (1432, 'UTC'),
  (1621, 'VST'),
  (1556, 'W-SU'),
  (1434, 'WET'),
  (1435, 'Zulu');

-- First Test User

INSERT INTO `user` (`id`, `username`, `password`, `type`, `name`, `lastname`, `since`, `lastlogin`, `modified`) VALUES
  (2908, 'testuser', 'dc724af18fbdd4e59189f5fe768a5f8311527050', 1, 'Test', 'User', 2014, '2014-03-29 00:00:00',
   '2014-03-29 15:10:48');

INSERT INTO `user_bio` (`id`, `user_id`, `content`, `modified`) VALUES
  (2187, 2908, 'testing bio', '2014-03-29 15:11:47');

INSERT INTO `user_contact` (`id`, `user_id`, `email`, `icq`, `aim`, `yahoo`, `msn`, `hp_uri`, `hp_title`, `phone`)
VALUES
  (2168, 2908, 'test@justjournal.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO `user_pref` (`id`, `user_id`, `allow_spider`, `style`, `owner_view_only`, `show_avatar`, `journal_name`, `ping_services`, `modified`)
VALUES
  (2192, 2908, 'Y', 1, 'N', 'N', 'Testing Journal', 'N', '2014-03-29 15:12:53');

-- Second Test User

INSERT INTO `user` (`id`, `username`, `password`, `type`, `name`, `lastname`, `since`, `lastlogin`, `modified`) VALUES
  (1, 'jjtest', 'dc724af18fbdd4e59189f5fe768a5f8311527050', 1, 'JJ', 'Test', 2014, '2014-03-01 00:00:00',
   '2014-03-29 15:10:48');

INSERT INTO `user_bio` (`id`, `user_id`, `content`, `modified`) VALUES
  (1, 1, 'testing bio', '2014-03-29 15:11:47');

INSERT INTO `user_contact` (`id`, `user_id`, `email`, `icq`, `aim`, `yahoo`, `msn`, `hp_uri`, `hp_title`, `phone`)
VALUES
  (1, 1, 'test@justjournal.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO `user_pref` (`id`, `user_id`, `allow_spider`, `style`, `owner_view_only`, `show_avatar`, `journal_name`, `ping_services`, `modified`)
VALUES
  (1, 1, 'N', 1, 'N', 'N', 'JJ REAL DEAL Test Blog', 'N', '2014-03-29 15:12:53');

INSERT INTO `justjournal_test`.`entry` (`id`, `uid`, `date`, `modified`, `subject`, `mood`, `music`, `location`, `body`, `security`, `autoformat`, `allow_comments`, `email_comments`, `draft`, `attach_image`, `attach_file`)
VALUES (33661, '2908', CURRENT_DATE(), CURRENT_TIMESTAMP, 'test subject', '2', 'the rock band song', '2',
        'i am a body of a blog post in text format', '2', 'Y', 'Y', 'Y', 'N', '0', '0');
INSERT INTO `justjournal_test`.`entry` (`id`, `uid`, `date`, `modified`, `subject`, `mood`, `music`, `location`, `body`, `security`, `autoformat`, `allow_comments`, `email_comments`, `draft`, `attach_image`, `attach_file`)
VALUES (NULL, '2908', CURRENT_DATE(), CURRENT_TIMESTAMP, 'test subject 2', '3', 'the rock band song', '1',
        'i am a body of a blog post in text format', '1', 'Y', 'Y', 'Y', 'N', '0', '0');

-- Create friends

INSERT INTO `justjournal_test`.`friends` (
  `pk`,
  `id`,
  `friendid`
)
VALUES (
  NULL, '1', '2908'
), (
  NULL, '2908', '1'
);
