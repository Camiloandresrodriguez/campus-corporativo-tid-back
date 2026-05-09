SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS `campus_tid` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `campus_tid`;

DELETE FROM `attendance`;
DELETE FROM `grades`;
DELETE FROM `enrollments`;
DELETE FROM `announcements`;
DELETE FROM `courses`;
DELETE FROM `categories`;
DELETE FROM `users`;

ALTER TABLE `attendance` AUTO_INCREMENT = 1;
ALTER TABLE `grades` AUTO_INCREMENT = 1;
ALTER TABLE `enrollments` AUTO_INCREMENT = 1;
ALTER TABLE `announcements` AUTO_INCREMENT = 1;
ALTER TABLE `courses` AUTO_INCREMENT = 1;
ALTER TABLE `categories` AUTO_INCREMENT = 1;
ALTER TABLE `users` AUTO_INCREMENT = 1;

INSERT INTO `users` (`id`, `address`, `created_at`, `document`, `email`, `first_name`, `last_name`, `password_hash`, `phone`, `role`, `updated_at`) VALUES
(1, 'Calle 10 # 12-34', '2026-05-01 08:10:00.000000', '1017194872', 'correo@gmail.com', 'Jaime', 'Zaata', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3246720301', 'STUDENT', '2026-05-09 13:26:56.000000'),
(2, 'Av Principal 123', '2026-04-20 09:00:00.000000', '900123456', 'ana.gomez@campus.test', 'Ana', 'Gomez', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3001112233', 'TEACHER', '2026-05-09 14:00:00.000000'),
(3, 'Cra 45 #12-34', '2026-05-02 10:05:00.000000', '1020304050', 'luis.perez@campus.test', 'Luis', 'Perez', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3012223344', 'STUDENT', '2026-05-09 14:05:00.000000'),
(4, 'Calle 8 # 20-11', '2026-05-02 11:20:00.000000', '1002003004', 'maria.rodriguez@campus.test', 'Maria', 'Rodriguez', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3204005006', 'STUDENT', '2026-05-06 09:40:00.000000'),
(5, 'Av 30 # 15-90', '2026-05-03 07:45:00.000000', '1002003005', 'carlos.ramirez@campus.test', 'Carlos', 'Ramirez', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3115556677', 'STUDENT', '2026-05-07 12:10:00.000000'),
(6, 'Cra 12 # 9-18', '2026-05-03 13:10:00.000000', '1002003006', 'sofia.torres@campus.test', 'Sofia', 'Torres', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3009001122', 'STUDENT', '2026-05-08 16:22:00.000000'),
(7, 'Calle 52 # 7-55', '2026-05-04 09:25:00.000000', '1002003007', 'diego.herrera@campus.test', 'Diego', 'Herrera', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3152003344', 'STUDENT', '2026-05-09 11:02:00.000000'),
(8, 'Cra 80 # 23-10', '2026-05-04 10:50:00.000000', '1002003008', 'valentina.castro@campus.test', 'Valentina', 'Castro', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3128889900', 'STUDENT', '2026-05-09 12:30:00.000000'),
(9, 'Av 68 # 40-21', '2026-05-05 08:35:00.000000', '1002003009', 'juan.martinez@campus.test', 'Juan', 'Martinez', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3017771122', 'STUDENT', '2026-05-09 09:15:00.000000'),
(10, 'Calle 19 # 33-12', '2026-05-05 14:05:00.000000', '1002003010', 'camila.lopez@campus.test', 'Camila', 'Lopez', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3101239876', 'STUDENT', '2026-05-09 14:22:00.000000'),
(11, 'Cra 7 # 70-01', '2026-05-06 09:10:00.000000', '1002003011', 'andres.sanchez@campus.test', 'Andres', 'Sanchez', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3004455667', 'STUDENT', '2026-05-09 10:30:00.000000'),
(12, 'Av 1 # 1-01', '2026-05-06 12:45:00.000000', '1002003012', 'natalia.vega@campus.test', 'Natalia', 'Vega', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3161010101', 'STUDENT', '2026-05-09 12:05:00.000000'),
(13, 'Cra 50 # 16-72', '2026-04-25 08:55:00.000000', '900123457', 'laura.diaz@campus.test', 'Laura', 'Diaz', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3003332211', 'TEACHER', '2026-05-09 09:05:00.000000'),
(14, 'Calle 100 # 9-90', '2026-04-26 10:15:00.000000', '900123458', 'felipe.moreno@campus.test', 'Felipe', 'Moreno', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3029090909', 'TEACHER', '2026-05-09 09:08:00.000000'),
(15, 'Sede Principal', '2026-04-15 08:00:00.000000', '800900100', 'admin@campus.test', 'Admin', 'System', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3000000000', 'ADMIN', '2026-05-09 08:00:00.000000'),
(16, 'Cra 3 # 14-22', '2026-05-07 07:30:00.000000', '1002003016', 'pedro.rojas@campus.test', 'Pedro', 'Rojas', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3182223344', 'STUDENT', '2026-05-09 13:40:00.000000'),
(17, 'Calle 27 # 5-19', '2026-05-07 10:20:00.000000', '1002003017', 'paola.navarro@campus.test', 'Paola', 'Navarro', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3194455667', 'STUDENT', '2026-05-09 13:42:00.000000'),
(18, 'Av 9 # 44-02', '2026-05-08 09:55:00.000000', '1002003018', 'kevin.ortiz@campus.test', 'Kevin', 'Ortiz', 'v1$_xg5GYSHZkTDlN9CHimsvg$tr7cf5mfD6ctbYp-HT6YSxwOjGePtAGM2c4VVTZ8nmk', '3171234567', 'STUDENT', '2026-05-09 13:44:00.000000');

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Programacion'),
(2, 'Diseno'),
(3, 'Marketing'),
(4, 'Data'),
(5, 'Idiomas'),
(6, 'Emprendimiento');

INSERT INTO `courses` (`id`, `active`, `capacity`, `description`, `end_date`, `start_date`, `title`, `category_id`) VALUES
(1, b'1', 30, 'Fundamentos de JavaScript: variables, condiciones, ciclos y funciones.', '2026-06-30', '2026-05-10', 'JavaScript Basico', 1),
(2, b'1', 25, 'Rutas, loaders y actions con React Router 6.4+.', '2026-06-15', '2026-05-12', 'React Router Data APIs', 1),
(3, b'1', 20, 'Principios de diseno, tipografia y composicion para interfaces.', '2026-06-20', '2026-05-15', 'Diseno de Interfaces', 2),
(4, b'1', 40, 'Introduccion a estrategias de marketing digital y analitica.', '2026-06-10', '2026-05-05', 'Marketing Digital Intro', 3),
(5, b'1', 35, 'Consultas SQL, joins, indices y normalizacion aplicada.', '2026-06-25', '2026-05-08', 'SQL y Modelado Relacional', 4),
(6, b'1', 30, 'Tablas dinamicas, formulas y dashboards para analitica.', '2026-06-28', '2026-05-09', 'Analitica con Excel', 4),
(7, b'1', 28, 'Lectura tecnica, vocabulario y comunicacion en contextos TI.', '2026-07-05', '2026-05-11', 'Ingles Tecnico para TI', 5),
(8, b'1', 22, 'Metodos de investigacion, entrevistas y pruebas de usabilidad.', '2026-06-26', '2026-05-13', 'Fundamentos de UX Research', 2),
(9, b'1', 26, 'Construccion de APIs REST con Node.js, validacion y buenas practicas.', '2026-07-10', '2026-05-14', 'Node.js APIs REST', 1),
(10, b'1', 40, 'Control de versiones, ramas, PRs y flujos colaborativos.', '2026-06-12', '2026-05-06', 'Git y GitHub Flujo de Trabajo', 1),
(11, b'1', 18, 'Propuesta de valor, prototipo, MVP y presentacion de pitch.', '2026-06-30', '2026-05-16', 'Emprendimiento: MVP y Pitch', 6),
(12, b'0', 30, 'Segmentacion, anuncios, presupuesto y medicion de resultados.', '2026-05-31', '2026-05-01', 'Publicidad en Redes Sociales', 3);

INSERT INTO `announcements` (`id`, `content`, `created_at`, `date`, `title`) VALUES
(1, 'Bienvenido(a) al campus. Revisa tus cursos y completa tu perfil.', '2026-05-01 08:30:00.000000', '2026-05-01', 'Bienvenida'),
(2, 'Ya estan disponibles los nuevos cursos de programacion y diseno.', '2026-05-02 09:00:00.000000', '2026-05-02', 'Nuevos cursos'),
(3, 'Recuerda actualizar tu informacion de contacto para recibir notificaciones.', '2026-05-03 10:15:00.000000', '2026-05-03', 'Actualiza tu perfil'),
(4, 'Semana de induccion: participa en las sesiones de herramientas y plataformas.', '2026-05-04 07:45:00.000000', '2026-05-04', 'Induccion'),
(5, 'Se habilitaron cupos adicionales en Git y GitHub Flujo de Trabajo.', '2026-05-06 12:05:00.000000', '2026-05-06', 'Cupos disponibles'),
(6, 'Entrega de la primera actividad en JavaScript Basico: fecha limite 2026-05-18.', '2026-05-10 08:10:00.000000', '2026-05-10', 'Actividad 1'),
(7, 'Taller de SQL: practica joins y normalizacion con ejercicios guiados.', '2026-05-12 09:20:00.000000', '2026-05-12', 'Taller SQL'),
(8, 'Charla de emprendimiento: valida tu idea y define tu MVP.', '2026-05-15 11:00:00.000000', '2026-05-15', 'Charla MVP');

INSERT INTO `enrollments` (`id`, `enrolled_at`, `status`, `course_id`, `user_id`) VALUES
(1, '2026-05-09 14:15:00.000000', 'INSCRITO', 1, 1),
(2, '2026-05-09 14:16:00.000000', 'INSCRITO', 2, 1),
(3, '2026-05-09 14:19:00.000000', 'INSCRITO', 10, 1),
(4, '2026-05-09 14:22:00.000000', 'INSCRITO', 5, 1),
(5, '2026-05-09 14:17:00.000000', 'INSCRITO', 3, 3),
(6, '2026-05-09 14:18:00.000000', 'COMPLETADO', 4, 3),
(7, '2026-05-09 14:24:00.000000', 'INSCRITO', 11, 3),
(8, '2026-05-09 14:25:00.000000', 'INSCRITO', 8, 3),
(9, '2026-05-07 09:10:00.000000', 'INSCRITO', 1, 4),
(10, '2026-05-07 09:12:00.000000', 'INSCRITO', 5, 4),
(11, '2026-05-07 09:20:00.000000', 'INSCRITO', 7, 4),
(12, '2026-05-07 09:40:00.000000', 'INSCRITO', 9, 5),
(13, '2026-05-07 09:42:00.000000', 'INSCRITO', 10, 5),
(14, '2026-05-07 09:50:00.000000', 'INSCRITO', 5, 5),
(15, '2026-05-08 10:00:00.000000', 'INSCRITO', 2, 6),
(16, '2026-05-08 10:03:00.000000', 'INSCRITO', 3, 6),
(17, '2026-05-08 10:05:00.000000', 'INSCRITO', 8, 6),
(18, '2026-05-08 11:20:00.000000', 'INSCRITO', 1, 7),
(19, '2026-05-08 11:25:00.000000', 'INSCRITO', 9, 7),
(20, '2026-05-08 12:10:00.000000', 'INSCRITO', 12, 8),
(21, '2026-05-08 12:15:00.000000', 'INSCRITO', 4, 8),
(22, '2026-05-08 13:05:00.000000', 'INSCRITO', 6, 9),
(23, '2026-05-08 13:10:00.000000', 'INSCRITO', 5, 9),
(24, '2026-05-08 13:15:00.000000', 'INSCRITO', 10, 9),
(25, '2026-05-09 09:05:00.000000', 'INSCRITO', 7, 10),
(26, '2026-05-09 09:06:00.000000', 'INSCRITO', 1, 10),
(27, '2026-05-09 10:30:00.000000', 'INSCRITO', 11, 11),
(28, '2026-05-09 10:32:00.000000', 'INSCRITO', 10, 11),
(29, '2026-05-09 11:15:00.000000', 'INSCRITO', 6, 12),
(30, '2026-05-09 11:17:00.000000', 'INSCRITO', 12, 12),
(31, '2026-05-09 12:40:00.000000', 'INSCRITO', 5, 16),
(32, '2026-05-09 12:42:00.000000', 'INSCRITO', 9, 16),
(33, '2026-05-09 12:44:00.000000', 'INSCRITO', 2, 16),
(34, '2026-05-09 13:00:00.000000', 'INSCRITO', 3, 17),
(35, '2026-05-09 13:02:00.000000', 'INSCRITO', 7, 17),
(36, '2026-05-09 13:04:00.000000', 'INSCRITO', 8, 17),
(37, '2026-05-09 13:20:00.000000', 'INSCRITO', 1, 18),
(38, '2026-05-09 13:22:00.000000', 'INSCRITO', 4, 18),
(39, '2026-05-09 13:24:00.000000', 'INSCRITO', 12, 18);

INSERT INTO `attendance` (`id`, `date`, `status`, `course_id`, `user_id`) VALUES
(1, '2026-05-10', 'PRESENTE', 1, 1),
(2, '2026-05-11', 'TARDANZA', 1, 1),
(3, '2026-05-13', 'PRESENTE', 1, 1),
(4, '2026-05-12', 'PRESENTE', 2, 1),
(5, '2026-05-14', 'PRESENTE', 2, 1),
(6, '2026-05-08', 'PRESENTE', 10, 1),
(7, '2026-05-10', 'PRESENTE', 10, 1),
(8, '2026-05-09', 'PRESENTE', 5, 1),
(9, '2026-05-16', 'TARDANZA', 5, 1),

(10, '2026-05-16', 'AUSENTE', 3, 3),
(11, '2026-05-17', 'PRESENTE', 3, 3),
(12, '2026-05-20', 'PRESENTE', 3, 3),
(13, '2026-05-06', 'PRESENTE', 4, 3),
(14, '2026-05-13', 'PRESENTE', 4, 3),
(15, '2026-05-18', 'PRESENTE', 11, 3),
(16, '2026-05-25', 'TARDANZA', 11, 3),
(17, '2026-05-14', 'PRESENTE', 8, 3),
(18, '2026-05-21', 'PRESENTE', 8, 3),

(19, '2026-05-10', 'PRESENTE', 1, 4),
(20, '2026-05-11', 'PRESENTE', 1, 4),
(21, '2026-05-09', 'PRESENTE', 5, 4),
(22, '2026-05-16', 'PRESENTE', 5, 4),
(23, '2026-05-12', 'TARDANZA', 7, 4),
(24, '2026-05-19', 'PRESENTE', 7, 4),

(25, '2026-05-16', 'PRESENTE', 9, 5),
(26, '2026-05-23', 'TARDANZA', 9, 5),
(27, '2026-05-08', 'PRESENTE', 10, 5),
(28, '2026-05-10', 'PRESENTE', 10, 5),
(29, '2026-05-09', 'PRESENTE', 5, 5),
(30, '2026-05-16', 'AUSENTE', 5, 5),

(31, '2026-05-12', 'PRESENTE', 2, 6),
(32, '2026-05-14', 'PRESENTE', 2, 6),
(33, '2026-05-16', 'PRESENTE', 3, 6),
(34, '2026-05-17', 'PRESENTE', 3, 6),
(35, '2026-05-14', 'PRESENTE', 8, 6),
(36, '2026-05-21', 'TARDANZA', 8, 6),

(37, '2026-05-10', 'PRESENTE', 1, 7),
(38, '2026-05-11', 'AUSENTE', 1, 7),
(39, '2026-05-16', 'PRESENTE', 9, 7),
(40, '2026-05-23', 'PRESENTE', 9, 7),

(41, '2026-05-06', 'PRESENTE', 12, 8),
(42, '2026-05-13', 'PRESENTE', 12, 8),
(43, '2026-05-06', 'PRESENTE', 4, 8),
(44, '2026-05-13', 'AUSENTE', 4, 8),

(45, '2026-05-10', 'PRESENTE', 6, 9),
(46, '2026-05-17', 'PRESENTE', 6, 9),
(47, '2026-05-09', 'PRESENTE', 5, 9),
(48, '2026-05-16', 'PRESENTE', 5, 9),
(49, '2026-05-08', 'PRESENTE', 10, 9),
(50, '2026-05-10', 'TARDANZA', 10, 9),

(51, '2026-05-12', 'PRESENTE', 7, 10),
(52, '2026-05-19', 'PRESENTE', 7, 10),
(53, '2026-05-10', 'PRESENTE', 1, 10),
(54, '2026-05-11', 'TARDANZA', 1, 10),

(55, '2026-05-18', 'PRESENTE', 11, 11),
(56, '2026-05-25', 'PRESENTE', 11, 11),
(57, '2026-05-08', 'PRESENTE', 10, 11),
(58, '2026-05-10', 'PRESENTE', 10, 11),

(59, '2026-05-10', 'PRESENTE', 6, 12),
(60, '2026-05-17', 'TARDANZA', 6, 12),
(61, '2026-05-06', 'PRESENTE', 12, 12),
(62, '2026-05-13', 'PRESENTE', 12, 12),

(63, '2026-05-09', 'PRESENTE', 5, 16),
(64, '2026-05-16', 'PRESENTE', 5, 16),
(65, '2026-05-16', 'PRESENTE', 9, 16),
(66, '2026-05-23', 'PRESENTE', 9, 16),
(67, '2026-05-12', 'AUSENTE', 2, 16),
(68, '2026-05-14', 'PRESENTE', 2, 16),

(69, '2026-05-16', 'PRESENTE', 3, 17),
(70, '2026-05-17', 'TARDANZA', 3, 17),
(71, '2026-05-12', 'PRESENTE', 7, 17),
(72, '2026-05-19', 'PRESENTE', 7, 17),
(73, '2026-05-14', 'PRESENTE', 8, 17),
(74, '2026-05-21', 'AUSENTE', 8, 17),

(75, '2026-05-10', 'PRESENTE', 1, 18),
(76, '2026-05-11', 'PRESENTE', 1, 18),
(77, '2026-05-06', 'PRESENTE', 4, 18),
(78, '2026-05-13', 'PRESENTE', 4, 18),
(79, '2026-05-06', 'PRESENTE', 12, 18),
(80, '2026-05-13', 'TARDANZA', 12, 18);

INSERT INTO `grades` (`id`, `date`, `note`, `score`, `course_id`, `user_id`) VALUES
(1, '2026-05-18', 'Actividad 1', 4.2, 1, 1),
(2, '2026-05-25', 'Quiz 1', 4.5, 1, 1),
(3, '2026-05-20', 'Quiz rutas', 4.6, 2, 1),
(4, '2026-05-28', 'Proyecto rutas', 4.3, 2, 1),
(5, '2026-05-11', 'Taller ramas', 4.7, 10, 1),
(6, '2026-05-22', 'Pull requests', 4.4, 10, 1),
(7, '2026-05-19', 'Joins basicos', 4.1, 5, 1),
(8, '2026-05-26', 'Normalizacion', 4.0, 5, 1),

(9, '2026-05-22', 'Wireframe', 4.0, 3, 3),
(10, '2026-05-29', 'Prototipo', 4.2, 3, 3),
(11, '2026-05-15', 'Campana 1', 3.5, 4, 3),
(12, '2026-05-23', 'Analitica basica', 3.8, 4, 3),
(13, '2026-05-27', 'Pitch 1', 4.4, 11, 3),
(14, '2026-06-03', 'Pitch final', 4.6, 11, 3),
(15, '2026-05-20', 'Guia entrevistas', 4.1, 8, 3),
(16, '2026-05-27', 'Prueba usabilidad', 4.0, 8, 3),

(17, '2026-05-18', 'Actividad 1', 4.0, 1, 4),
(18, '2026-05-25', 'Quiz 1', 3.9, 1, 4),
(19, '2026-05-19', 'Joins basicos', 3.8, 5, 4),
(20, '2026-05-26', 'Normalizacion', 4.0, 5, 4),
(21, '2026-05-21', 'Reading 1', 4.3, 7, 4),
(22, '2026-05-28', 'Reading 2', 4.1, 7, 4),

(23, '2026-05-21', 'CRUD API', 4.2, 9, 5),
(24, '2026-05-29', 'Auth basica', 3.9, 9, 5),
(25, '2026-05-11', 'Taller ramas', 4.6, 10, 5),
(26, '2026-05-22', 'Pull requests', 4.2, 10, 5),
(27, '2026-05-19', 'Joins basicos', 3.7, 5, 5),
(28, '2026-05-26', 'Normalizacion', 3.5, 5, 5),

(29, '2026-05-20', 'Proyecto rutas', 4.1, 2, 6),
(30, '2026-05-28', 'Integracion datos', 4.0, 2, 6),
(31, '2026-05-22', 'Wireframe', 4.4, 3, 6),
(32, '2026-05-29', 'Prototipo', 4.3, 3, 6),
(33, '2026-05-20', 'Guia entrevistas', 4.2, 8, 6),
(34, '2026-05-27', 'Prueba usabilidad', 4.1, 8, 6),

(35, '2026-05-18', 'Actividad 1', 3.6, 1, 7),
(36, '2026-05-25', 'Quiz 1', 3.8, 1, 7),
(37, '2026-05-21', 'CRUD API', 4.0, 9, 7),
(38, '2026-05-29', 'Auth basica', 4.1, 9, 7),

(39, '2026-05-15', 'Campana 1', 4.0, 12, 8),
(40, '2026-05-23', 'Creativos', 3.9, 12, 8),
(41, '2026-05-15', 'Campana 1', 3.4, 4, 8),
(42, '2026-05-23', 'Analitica basica', 3.6, 4, 8),

(43, '2026-05-24', 'Dashboard 1', 4.5, 6, 9),
(44, '2026-05-31', 'Dashboard 2', 4.2, 6, 9),
(45, '2026-05-19', 'Joins basicos', 4.0, 5, 9),
(46, '2026-05-26', 'Normalizacion', 3.9, 5, 9),
(47, '2026-05-11', 'Taller ramas', 4.1, 10, 9),
(48, '2026-05-22', 'Pull requests', 4.0, 10, 9),

(49, '2026-05-21', 'Reading 1', 4.2, 7, 10),
(50, '2026-05-28', 'Reading 2', 4.0, 7, 10),
(51, '2026-05-18', 'Actividad 1', 4.1, 1, 10),
(52, '2026-05-25', 'Quiz 1', 3.9, 1, 10),

(53, '2026-05-27', 'Pitch 1', 4.0, 11, 11),
(54, '2026-06-03', 'Pitch final', 4.2, 11, 11),
(55, '2026-05-11', 'Taller ramas', 4.4, 10, 11),
(56, '2026-05-22', 'Pull requests', 4.3, 10, 11),

(57, '2026-05-24', 'Dashboard 1', 4.1, 6, 12),
(58, '2026-05-31', 'Dashboard 2', 3.9, 6, 12),
(59, '2026-05-15', 'Campana 1', 3.8, 12, 12),
(60, '2026-05-23', 'Creativos', 4.0, 12, 12),

(61, '2026-05-19', 'Joins basicos', 4.4, 5, 16),
(62, '2026-05-26', 'Normalizacion', 4.1, 5, 16),
(63, '2026-05-21', 'CRUD API', 4.3, 9, 16),
(64, '2026-05-29', 'Auth basica', 4.0, 9, 16),
(65, '2026-05-20', 'Quiz rutas', 4.0, 2, 16),
(66, '2026-05-28', 'Proyecto rutas', 3.9, 2, 16),

(67, '2026-05-22', 'Wireframe', 3.7, 3, 17),
(68, '2026-05-29', 'Prototipo', 3.9, 3, 17),
(69, '2026-05-21', 'Reading 1', 4.0, 7, 17),
(70, '2026-05-28', 'Reading 2', 4.1, 7, 17),
(71, '2026-05-20', 'Guia entrevistas', 3.8, 8, 17),
(72, '2026-05-27', 'Prueba usabilidad', 3.6, 8, 17),

(73, '2026-05-18', 'Actividad 1', 4.3, 1, 18),
(74, '2026-05-25', 'Quiz 1', 4.2, 1, 18),
(75, '2026-05-15', 'Campana 1', 3.9, 4, 18),
(76, '2026-05-23', 'Analitica basica', 4.0, 4, 18),
(77, '2026-05-15', 'Campana 1', 4.1, 12, 18),
(78, '2026-05-23', 'Creativos', 4.2, 12, 18);

SET FOREIGN_KEY_CHECKS = 1;
COMMIT;
