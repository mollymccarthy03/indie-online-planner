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

-- Insert sample tasks into the `tasks` table (chatgpt generated)
INSERT INTO tasks (title, description, todo_date, due_date, user_id)
VALUES
    ('Complete homework', 'Finish the math and science assignments', 20241125, 20241128, 1),
    ('Grocery shopping', 'Buy milk, eggs, bread, and coffee', 20241126, 20241126, 1),
    ('Team meeting', 'Discuss the Q4 project goals and deliverables', 20241127, 20241127, 1),
    ('Doctor appointment', 'Annual check-up at 10:00 AM', 20241128, 20241128, 1),
    ('Read', 'Read the first 50 pages of "The Great Gatsby"', 20241129, 20241202, 1),
    ('Prepare presentation', 'Prepare slides for the sales pitch', 20241130, 20241201, 1),
    ('Submit report', 'Submit the weekly progress report to the manager', 20241201, 20241201, 3),
    ('Plan weekend trip', 'Research destinations and book tickets for a short vacation', 20241202, 20241203, 3),
    ('Fix leaking faucet', 'Replace washer and check for any other leaks', 20241203, 20241204, 5),
    ('Practice piano', 'Practice for at least 30 minutes', 20241204, 20241205, 5);