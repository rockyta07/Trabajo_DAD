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

        private Integer id;

        /*
        @OneToOne
        private Usuarios adp;*/

        @ManyToOne
        private Shelter prt;

        protected Messages(){}

        public Messages(String newCont){
            this.content = newCont;
            prt = new Shelter();
        }

        public String getContent(){
            return this.content;
        }

        public Usuarios getAdopter(){
            return this.adp;
        }

        public void setAdopter(Usuarios newAdp){
            this.adp = newAdp;
        }

        public Integer getId(){ return this.id;}
        public Shelter getPrt(){
            return this.prt;
        }

        public void setId(Integer id){ this.id = id;}
        public void setPrt(Shelter newPrt){
            this.prt = newPrt;
        }


}
