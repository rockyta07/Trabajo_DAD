package fluffandpaws.webadopcion.BBDD;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Messages {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id_msg;

        private String content;

        //private Integer id;

        /*
        @OneToOne
        private Usuarios adp;*/

        @ManyToOne
        private Shelter prt;

        protected Messages(){}

        public Messages(String newCont){
            this.content = newCont;
        }

        public String getContent(){
            return this.content;
        }

        //public Usuarios getAdopter(){
           // return this.prt;
        //}

        //public void setAdopter(Usuarios newAdp){
            //this.prt = newAdp;
        //}

        public Long getId(){ return this.id_msg;}
        public Shelter getPrt(){
            return this.prt;
        }

        public void setId(Long id){ this.id_msg = id;}
        public void setPrt(Shelter newPrt){
            this.prt = newPrt;
        }


}
