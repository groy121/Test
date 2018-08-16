(async()=>{
    const currentDocument = document.currentScript.ownerDocument;
    const res = await fetch('/components/my-widget-custom/my-widget-custom.html');
    const textTemplate = await res.text();
    const HTMLTemplate = new DOMParser().parseFromString(textTemplate, 'text/html')
        .querySelector('template');

    class MyWidgetCustom extends HTMLElement {
        constructor (){ super(); } 
        getUName(){
            return this.uname;
        }
        setUName(uname){
            this.uname = uname;
        }
        getUTrigram(){
            return this.utrigram;
        }
        setUTrigram(utrigram) {
            this.utrigram = utrigram;
        }
        getUUserId() {
            return this.utrigram + this.uname;
        }
        connectedCallback () {
            const shadowRoot = this.attachShadow({ mode: 'open' });
            const instance = HTMLTemplate.content.cloneNode(true);
            shadowRoot.appendChild(instance);
            this.setUName(this.getAttribute('uname'));
            this.setUTrigram(this.getAttribute('utrigram'));
            var that = this;
            
            (function(that){
                console.log(that);
                
                var btn = that.shadowRoot.querySelector('#my-widget-custom-button');
                function setHtml(that){
                    var str = "UserID : "+that.getUUserId()+"<br>Name : "+that.uname+"<br>Trigram : "+that.utrigram;
                    console.log(str);
                    var myWidget = document.getElementsByTagName("my-widget");
                    console.log(myWidget);
                    var selected = myWidget[0].shadowRoot.querySelector("#my-widget-user-details");
                    selected.innerHTML = str;
                }
                btn.addEventListener('click',function(){
                    setHtml(that);
                },false);
            })(that);
        }

        
    }
    
    customElements.define('my-widget-custom',MyWidgetCustom);
})();