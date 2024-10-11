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
                        `id` int NOT NULL AUTO_INCREMENT,  -- Auto-increment primary key
                        `day_of_week` varchar(10) NOT NULL,  -- Name of the day (e.g., 'Monday', 'Tuesday')
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- Insert the 7 days of the week into the `days` table
INSERT INTO `days` (`day_of_week`) VALUES
                                       ('Monday'), ('Tuesday'), ('Wednesday'), ('Thursday'), ('Friday'), ('Saturday'), ('Sunday');

-- Table structure for table `tasks`
CREATE TABLE `tasks` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `title` varchar(255) DEFAULT NULL,
                         `description` text,
                         `todo_date` int NOT NULL,  -- Foreign key linking to the days table (for planned day)
                         `due_date` int NOT NULL,   -- Foreign key linking to the days table (for due day)
                         PRIMARY KEY (`id`),
                         FOREIGN KEY (`todo_date`) REFERENCES `days`(`id`) ON DELETE CASCADE,  -- Foreign key for todo_date
                         FOREIGN KEY (`due_date`) REFERENCES `days`(`id`) ON DELETE CASCADE   -- Foreign key for due_date
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert data for the `week` table (representing a week)
INSERT IGNORE INTO `week` (`id`)
VALUES (1);


-- Insert tasks and link them to specific days in the `days` table
-- The `todo_date` references the `days.id` for the day you plan to do the task
-- The `due_date` references the `days.id` for the day the task is due
INSERT IGNORE INTO `tasks` (`id`, `title`, `description`, `todo_date`, `due_date`)
VALUES (1, 'Finish Planner', 'Complete the online planner project', 1, 4),  -- to do Monday, due Thursday
       (2, 'Prepare Report', 'Write the report for the meeting', 3, 5);    -- to do Wednesday, due Friday
