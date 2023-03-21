ALTER TABLE `filter_use` 
ADD COLUMN `is_industrial` TINYINT(1) NOT NULL DEFAULT '0' AFTER `filter_use_label`;
