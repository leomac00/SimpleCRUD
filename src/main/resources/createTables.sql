USE mydatabase;
CREATE TABLE `mydatabase`.`person` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NULL,
	UNIQUE INDEX `id_UNIQUE` (`id` ASC),
PRIMARY KEY (`id`));

CREATE TABLE `mydatabase`.`contact` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NULL,
	`email` VARCHAR(255) NULL,
	`phone` VARCHAR(45) NULL,
	`person_id` INT,
 
	FOREIGN KEY (`person_id`) REFERENCES person(`id`),
	UNIQUE INDEX `id_UNIQUE` (`id` ASC),
PRIMARY KEY (`id`));




 
