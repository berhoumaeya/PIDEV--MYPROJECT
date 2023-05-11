<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230510175822 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE reservation (id INT AUTO_INCREMENT NOT NULL, id_event INT DEFAULT NULL, id_user INT NOT NULL, qte INT NOT NULL, INDEX IDX_42C84955D52B4B97 (id_event), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C84955D52B4B97 FOREIGN KEY (id_event) REFERENCES evenement (id_event)');
        $this->addSql('DROP TABLE promotion');
        $this->addSql('ALTER TABLE evenement DROP FOREIGN KEY FK_B26681EE4656696');
        $this->addSql('ALTER TABLE evenement ADD CONSTRAINT FK_B26681EE4656696 FOREIGN KEY (destin_id) REFERENCES destination (id)');
        $this->addSql('ALTER TABLE publicite DROP FOREIGN KEY publicite_ibfk_1');
        $this->addSql('ALTER TABLE publicite DROP FOREIGN KEY publicite_ibfk_1');
        $this->addSql('ALTER TABLE publicite CHANGE id_event id_event INT DEFAULT NULL');
        $this->addSql('ALTER TABLE publicite ADD CONSTRAINT FK_1D394E39D52B4B97 FOREIGN KEY (id_event) REFERENCES evenement (id_event)');
        $this->addSql('DROP INDEX id_event ON publicite');
        $this->addSql('CREATE INDEX IDX_1D394E39D52B4B97 ON publicite (id_event)');
        $this->addSql('ALTER TABLE publicite ADD CONSTRAINT publicite_ibfk_1 FOREIGN KEY (id_event) REFERENCES evenement (id_event) ON UPDATE CASCADE ON DELETE CASCADE');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE promotion (id_prom INT AUTO_INCREMENT NOT NULL, id_client INT NOT NULL, remise INT NOT NULL, PRIMARY KEY(id_prom)) DEFAULT CHARACTER SET latin1 COLLATE `latin1_swedish_ci` ENGINE = MyISAM COMMENT = \'\' ');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C84955D52B4B97');
        $this->addSql('DROP TABLE reservation');
        $this->addSql('ALTER TABLE evenement DROP FOREIGN KEY FK_B26681EE4656696');
        $this->addSql('ALTER TABLE evenement ADD CONSTRAINT FK_B26681EE4656696 FOREIGN KEY (destin_id) REFERENCES destination (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE publicite DROP FOREIGN KEY FK_1D394E39D52B4B97');
        $this->addSql('ALTER TABLE publicite DROP FOREIGN KEY FK_1D394E39D52B4B97');
        $this->addSql('ALTER TABLE publicite CHANGE id_event id_event INT NOT NULL');
        $this->addSql('ALTER TABLE publicite ADD CONSTRAINT publicite_ibfk_1 FOREIGN KEY (id_event) REFERENCES evenement (id_event) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('DROP INDEX idx_1d394e39d52b4b97 ON publicite');
        $this->addSql('CREATE INDEX id_event ON publicite (id_event)');
        $this->addSql('ALTER TABLE publicite ADD CONSTRAINT FK_1D394E39D52B4B97 FOREIGN KEY (id_event) REFERENCES evenement (id_event)');
    }
}
