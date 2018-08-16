(async()=>{
    const currentDocument = document.currentScript.ownerDocument;
    const res = await fetch('/components/my-widget/my-widget.html');
    const textTemplate = await res.text();
    const HTMLTemplate = new DOMParser().parseFromString(textTemplate, 'text/html')
        .querySelector('template');

    class MyWidget extends HTMLElement {
        constructor (){ super(); } 
        getName(){
            return this.name;
        }
        setName (name){
            this.name = name;
        }
        getTrigram(){
            return this.trigram;
        }
        setTrigram(trigram) {
            this.trigram = trigram;
        }
        getUserId () {
            return this.trigram + this.name;
        }

        connectedCallback () {
            const shadowRoot = this.attachShadow({ mode: 'open' });
            const instance = HTMLTemplate.content.cloneNode(true);
            this.setName(this.getAttribute('myname'));
            this.setTrigram(this.getAttribute('trigram'));
            var doc = document.createElement('div');
            doc.className="my-widget-user-details";
            doc.id = "my-widget-user-details";
            doc.innerHTML = "UserID : "+this.getUserId()+"<br>Name : "+this.getName()+"<br>Trigram : "+this.getTrigram();
            instance.appendChild(doc);
            shadowRoot.appendChild(instance);
        }
    }
    
    customElements.define('my-widget',MyWidget);
})();