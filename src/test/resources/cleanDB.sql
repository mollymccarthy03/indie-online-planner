-- Drop existing tables if they exist
DROP TABLE IF EXISTS `tasks`;
DROP TABLE IF EXISTS `days`;
DROP TABLE IF EXISTS `week`;

-- Table structure for table `week`
CREATE TABLE `week` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- Table structure for table `days`
CREATE TABLE `days` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `week_id` int NOT NULL,  -- Foreign key linking to the week table
                        `day_of_week` varchar(10) NOT NULL,  -- Store the name of the day (e.g., 'Monday', 'Tuesday')
                        PRIMARY KEY (`id`),
                        FOREIGN KEY (`week_id`) REFERENCES `week`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- Table structure for table `tasks`
CREATE TABLE `tasks` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `title` varchar(255) DEFAULT NULL,
                         `description` text,
                         `todo_date` date DEFAULT NULL,
                         `due_date` date DEFAULT NULL,
                         `day_id` int DEFAULT NULL,  -- Foreign key linking to the days table
                         PRIMARY KEY (`id`),
                         FOREIGN KEY (`day_id`) REFERENCES `days`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- Insert data for the `week` table (representing a week)
INSERT IGNORE INTO `week` (`id`)
VALUES (1);

-- Insert data for the `days` table (representing days of the week for a specific week)
INSERT IGNORE INTO `days` (`week_id`, `day_of_week`)
VALUES (1, 'Monday'),
       (1, 'Tuesday'),
       (1, 'Wednesday'),
       (1, 'Thursday'),
       (1, 'Friday'),
       (1, 'Saturday'),
       (1, 'Sunday');

-- Insert tasks and link them to specific days in the `days` table
INSERT IGNORE INTO `tasks` (`id`, `title`, `description`, `todo_date`, `due_date`, `day_id`)
VALUES (1, 'Finish Planner', 'Complete the online planner project', '2024-10-10', '2024-10-12', 4), -- Thursday
       (2, 'Prepare Report', 'Write the report for the meeting', '2024-10-09', '2024-10-11', 3);  -- Wednesday
