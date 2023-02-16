package fluffandpaws.webadopcion.BBDD;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Messages {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id_msg;

        private String content;

        @OneToOne
        private Users adp;

        @OneToOne
        private Shelter prt;

        protected Messages(){}

        public Messages(String newCont){
            this.content = newCont;
            prt = new Shelter();
        }

        public String getContent(){
            return this.content;
        }

        public Users getAdopter(){
            return this.adp;
        }

        public void setAdopter(Users newAdp){
            this.adp = newAdp;
        }

        public Shelter getPrt(){
            return this.prt;
        }

        public void setPrt(Shelter newPrt){
            this.prt = newPrt;
        }


}
