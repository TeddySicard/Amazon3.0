package commerce.catalogue.domaine.modele;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity (name="commerce.catalogue.domaine.modele.DVD")
@DiscriminatorValue("DVD")
public class DVD extends Article {}
