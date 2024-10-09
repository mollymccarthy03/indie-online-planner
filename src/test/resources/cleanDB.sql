-- Table structure for table `tasks`
DROP TABLE IF EXISTS `tasks`;
CREATE TABLE `tasks` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `title` varchar(255) DEFAULT NULL,
                         `description` text,
                         `todo_date` date DEFAULT NULL,
                         `due_date` date DEFAULT NULL,
                         `week_id` int DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `fk_week` (`week_id`),
                         CONSTRAINT `fk_week` FOREIGN KEY (`week_id`) REFERENCES `week` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table `tasks`
INSERT IGNORE INTO `tasks` (`id`, `title`, `description`, `todo_date`, `due_date`, `week_id`)
VALUES (4,'Finish Planner','Complete the online planner project','2024-10-10','2024-10-12',1);

-- Table structure for table `week`
DROP TABLE IF EXISTS `week`;
CREATE TABLE `week` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `mon` date DEFAULT NULL,
                        `tues` date DEFAULT NULL,
                        `weds` date DEFAULT NULL,
                        `thurs` date DEFAULT NULL,
                        `fri` date DEFAULT NULL,
                        `sat` date DEFAULT NULL,
                        `sun` date DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table `week`
INSERT IGNORE INTO `week` (`id`, `mon`, `tues`, `weds`, `thurs`, `fri`, `sat`, `sun`)
VALUES (1,'2024-10-07','2024-10-08','2024-10-09','2024-10-10','2024-10-11','2024-10-12','2024-10-13');