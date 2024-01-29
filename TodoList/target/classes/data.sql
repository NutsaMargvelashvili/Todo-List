CREATE TABLE TODO_LIST(
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    todo VARCHAR(60),
    status VARCHAR(50),
    create_date TIMESTAMP,
    in_progress_date TIMESTAMP,
    complete_date TIMESTAMP
);