package fluffandpaws.webadopcion.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Mensaje {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @JsonIgnore
        private Long id;

        private String sender;
        private String content;

        private String description;


        /*
        @OneToOne
        private Usuarios adp;*/

        @ManyToOne
        @JsonIgnore
        private Protectora prtInstance;

        protected Mensaje(){}

        public Mensaje(String eMail, String newCont){
            this.content = newCont;
            this.sender = eMail;
            this.description = toString();
        }

        public Mensaje(String eMail, String newCont, Protectora ptr){
                this.content = newCont;
                this.sender = eMail;
                this.prtInstance = ptr;
                this.description = toString();
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

        public String getSender(){
                return this.sender;
        }

        public void setSender(String newEMail) {
                this.sender = newEMail;
        }

        @Override
        public String toString(){
                return "[Mensaje enviado desde: " + this.sender + " a: " + prtInstance.getName() + "]: " + this.content;
        }

        public Protectora getPrtInstance(){
                return this.prtInstance;
        }

        public void setPrtInstance(Protectora p){
                this.prtInstance = p;
        }


        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }
}
