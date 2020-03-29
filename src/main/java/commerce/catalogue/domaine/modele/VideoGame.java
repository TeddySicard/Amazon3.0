package commerce.catalogue.domaine.modele;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity (name="commerce.catalogue.domaine.modele.VideoGame")
@DiscriminatorValue("VideoGame")
public class VideoGame extends Article {}
