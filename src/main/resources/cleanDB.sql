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
                         `todo_date` DATE NOT NULL,
                         `due_date` DATE NOT NULL,
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

-- Insert sample tasks into the `tasks` table (chatgpt generated)INSERT INTO tasks (title, description, todo_date, due_date, user_id)
INSERT INTO tasks (title, description, todo_date, due_date, user_id)
VALUES
    ('Complete homework', 'Finish the math and science assignments', '2024-12-09', '2024-12-11', 1),
    ('Grocery shopping', 'Buy milk, eggs, bread, and coffee', '2024-12-10', '2024-12-10', 1),
    ('Team meeting', 'Discuss the Q4 project goals and deliverables', '2024-12-11', '2024-12-11', 1),
    ('Doctor appointment', 'Annual check-up at 10:00 AM', '2024-12-12', '2024-12-12', 1),
    ('Read', 'Read the first 50 pages of "The Great Gatsby"', '2024-12-13', '2024-12-15', 1),
    ('Prepare presentation', 'Prepare slides for the sales pitch', '2024-12-14', '2024-12-15', 1),
    ('Submit report', 'Submit the weekly progress report to the manager', '2024-12-15', '2024-12-15', 3),
    ('Plan weekend trip', 'Research destinations and book tickets for a short vacation', '2024-12-16', '2024-12-17', 3),
    ('Fix leaking faucet', 'Replace washer and check for any other leaks', '2024-12-17', '2024-12-18', 5),
    ('Practice piano', 'Practice for at least 30 minutes', '2024-12-18', '2024-12-19', 5);