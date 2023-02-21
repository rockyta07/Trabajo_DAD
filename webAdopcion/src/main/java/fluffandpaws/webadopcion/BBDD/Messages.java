package fluffandpaws.webadopcion.BBDD;

import jakarta.persistence.*;

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

        //public Usuarios getAdopter(){
           // return this.prt;
        //}

        //public void setAdopter(Usuarios newAdp){
            //this.prt = newAdp;
        //}

        public Integer getId(){ return this.id;}
        public Shelter getPrt(){
            return this.prt;
        }

        public void setId(Integer id){ this.id = id;}
        public void setPrt(Shelter newPrt){
            this.prt = newPrt;
        }


}
