package fluffandpaws.webadopcion.BBDD;

import javax.persistence.*;

@Entity
public class Mensaje {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String eMailRemitente;
        private String content;



        /*
        @OneToOne
        private Usuarios adp;*/

        @ManyToOne
        private Protectora prtInstance;

        protected Mensaje(){}

        public Mensaje(String eMail, String newCont){
            this.content = newCont;
            this.eMailRemitente = eMail;
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
        public void setContent(String newContent){
                this.content = newContent;
        }

        public Long getId(){ return this.id;}

        public void setId(Long id){ this.id = id;}

        public String geteMailRemitente(){
                return this.eMailRemitente;
        }

        public void seteMailRemitente(String newEMail) {
                this.eMailRemitente = newEMail;
        }

        @Override
        public String toString(){
                return "[" + this.eMailRemitente + "]: " + this.content;
        }

        public Protectora getPrtInstance(){
                return this.prtInstance;
        }

        public void setPrtInstance(Protectora p){
                this.prtInstance = p;
        }




}
