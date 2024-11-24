-- Drop tables if they exist for clean startup/testing
DROP TABLE IF EXISTS `tasks`;
DROP TABLE IF EXISTS `users`;

-- Table structure for `users`
CREATE TABLE `users` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `username` varchar(50) NOT NULL,
                         `email` varchar(100) NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `username` (`username`),
                         UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table structure for `tasks`
CREATE TABLE `tasks` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `title` varchar(255) DEFAULT NULL,
                         `description` text,
                         `todo_date` int NOT NULL,
                         `due_date` int NOT NULL,
                         `user_id` int NOT NULL,
                         PRIMARY KEY (`id`),
                         KEY `fk_user_task` (`user_id`),
                         CONSTRAINT `fk_user_task` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Insert sample users into the `users` table
INSERT INTO users (username, email) VALUES ('john_doe', 'john.doe@example.com');
INSERT INTO users (username, email) VALUES ('no_one', 'no.one@example.com');
INSERT INTO users (username, email) VALUES ('molly_mccarthy', 'molly.mccarthy@example.com');
INSERT INTO users (username, email) VALUES ('russ_hawley', 'russ_hawley@example.com');
INSERT INTO users (username, email) VALUES ('martha_marble', 'martha_marble@example.com');