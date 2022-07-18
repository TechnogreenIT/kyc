ALTER TABLE `reg_sew_poll` 
CHANGE COLUMN `id` `id` BIGINT(30) NOT NULL ,
CHANGE COLUMN `poll_name` `poll_id` BIGINT(30) NULL DEFAULT NULL ,
ADD COLUMN `wwtp_id` BIGINT(30) NULL AFTER `id`;


ALTER TABLE `reg_sew_poll` 
ADD INDEX `reg_sew_poll_wwtp_id_idx` (`wwtp_id` ASC),
ADD INDEX `reg_sew_poll_poll_id_idx` (`poll_id` ASC);
ALTER TABLE `reg_sew_poll` 
ADD CONSTRAINT `reg_sew_poll_wwtp_id`
  FOREIGN KEY (`wwtp_id`)
  REFERENCES `wastewater_treatment` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `reg_sew_poll_poll_id`
  FOREIGN KEY (`poll_id`)
  REFERENCES `water_sew_poll_op` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `reg_eff_poll` 
CHANGE COLUMN `id` `id` BIGINT(30) NOT NULL ,
CHANGE COLUMN `poll_name` `poll_id` BIGINT(30) NULL DEFAULT NULL ,
ADD COLUMN `wwtp_id` BIGINT(30) NULL AFTER `id`;


ALTER TABLE `reg_eff_poll` 
ADD INDEX `wwtp_id_wwtp_idx` (`wwtp_id` ASC),
ADD INDEX `reg_eff_poll_poll_id_idx` (`poll_id` ASC);
ALTER TABLE `reg_eff_poll` 
ADD CONSTRAINT `wwtp_id_wwtp`
  FOREIGN KEY (`wwtp_id`)
  REFERENCES `wastewater_treatment` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `reg_eff_poll_poll_id`
  FOREIGN KEY (`poll_id`)
  REFERENCES `waterie_pollutant_op` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
