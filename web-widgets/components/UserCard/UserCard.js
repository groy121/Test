(async () => {
    const res = await fetch('/components/UserCard/UserCard.html');
    const textTemplate = await res.text();
    const scripts = document.getElementsByTagName("script"); 
    const thisScript = scripts[scripts.length - 1];
    const currentDocument = thisScript.ownerDocument;
    const HTMLTemplate = new DOMParser().parseFromString(textTemplate, 'text/html')
        .querySelector('template');


    class UserCard extends HTMLElement {
        constructor() {
            // If you define a constructor, always call super() first as it is required by the CE spec.
            super();

            // Setup a click listener on <user-card>
            this.addEventListener('click', e => {
                this.toggleCard();
            });
        }

        // Define setters to update the DOM whenever these values are set
        set username(value) {
            this._username = value;
            if (this.shadowRoot)
                this.shadowRoot.querySelector('#card__username').innerHTML = value;
        }

        get username() {
            return this._username;
        }

        set address(value) {
            this._address = value;
            if (this.shadowRoot)
                this.shadowRoot.querySelector('#card__address').innerHTML = value;
        }

        get address() {
            return this._address;
        }

        set isAdmin(value) {
            this._isAdmin = value;
            if (this.shadowRoot)
                this.shadowRoot.querySelector('#card__admin-flag').style.display = value == true ? "block" : "none";
        }

        get isAdmin() {
            return this._isAdmin;
        }

        // Getter to let component know what attributes
        // to watch for mutation
        static get observedAttributes() {
            return ['username', 'address', 'is-admin'];
        }


        // Called when element is inserted in DOM
        connectedCallback() {
            const shadowRoot = this.attachShadow({ mode: 'open' });

            // Select the template and clone it. Finally attach the cloned node to the shadowDOM's root.
            // Current document needs to be defined to get DOM access to imported HTML
            // const template = currentDocument.querySelector('#user-card-template');
           // const template = HTMLTemplate.querySelector('#user-card-template');
            const instance = HTMLTemplate.content.cloneNode(true);
            shadowRoot.appendChild(instance);

            // Extract the attribute user-id from our element. 
            // Note that we are going to specify our cards like: 
            // <user-card user-id="1"></user-card>
            const userId = this.getAttribute('user-id');
            // You can also put checks to see if attr is present or not
            // and throw errors to make some attributes mandatory
            // Also default values for these variables can be defined here
            this.username = this.getAttribute('username');
            this.address = this.getAttribute('address');
            this.isAdmin = this.getAttribute('is-admin');
            // Fetch the data for that user Id from the API and call the render method with this data
            fetch(`https://jsonplaceholder.typicode.com/users/${userId}`)
                .then((response) => response.text())
                .then((responseText) => {
                    this.render(JSON.parse(responseText));
                })
                .catch((error) => {
                    console.error(error);
                });
        }

        render(userData) {
            // Fill the respective areas of the card using DOM manipulation APIs
            // All of our components elements reside under shadow dom. So we created a this.shadowRoot property
            // We use this property to call selectors so that the DOM is searched only under this subtree
            this.shadowRoot.querySelector('.card__full-name').innerHTML = userData.name;
            this.shadowRoot.querySelector('.card__user-name').innerHTML = userData.username;
            this.shadowRoot.querySelector('.card__website').innerHTML = userData.website;
            this.shadowRoot.querySelector('.card__address').innerHTML = `<h4>Address</h4>
          ${userData.address.suite}, <br />
          ${userData.address.street},<br />
          ${userData.address.city},<br />
          Zipcode: ${userData.address.zipcode}`
        }

        toggleCard() {
            let elem = this.shadowRoot.querySelector('.card__hidden-content');
            let btn = this.shadowRoot.querySelector('.card__details-btn');
            btn.innerHTML = elem.style.display == 'none' ? 'Less Details' : 'More Details';
            elem.style.display = elem.style.display == 'none' ? 'block' : 'none';
        }

        attributeChangedCallback(attr, oldValue, newValue) {
            const attribute = attr.toLowerCase();
            console.log(newVal);
            if (attribute === 'username') {
                this.username = newVal != '' ? newVal : "Not Provided!";
            } else if (attribute === 'address') {
                this.address = newVal !== '' ? newVal : "Not Provided!";
            } else if (attribute === 'is-admin') {
                this.isAdmin = newVal == 'true';
            }
        }
    }

    customElements.define('user-card', UserCard);

})();