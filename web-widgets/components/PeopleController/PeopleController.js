(function () {
    const currentDocument = document.currentScript.ownerDocument;
  
    class PeopleController extends HTMLElement {
      constructor() {
        super();
        this.peopleList = [];
      }
  
      connectedCallback() {
        const shadowRoot = this.attachShadow({ mode: 'open' });
        const template = currentDocument.querySelector('#people-controller-template');
        const instance = template.content.cloneNode(true);
        shadowRoot.appendChild(instance);
  
        _fetchAndPopulateData(this);
      }
    }
  
    // Private functions here
    function _fetchAndPopulateData(self) {
        let peopleList = self.shadowRoot.querySelector('#people-list');
        fetch(`https://jsonplaceholder.typicode.com/users`)
          .then((response) => response.text())
          .then((responseText) => {
            const list = JSON.parse(responseText);
            self.peopleList = list;
            peopleList.list = list;
            console.log(list);
            _attachEventListener(self);
          })
          .catch((error) => {
            console.error(error);
          });
      }
      
      function _attachEventListener(self) {
        let personDetail = self.shadowRoot.querySelector('#person-detail');
      
        //Initialize with person with id 1:
        personDetail.updatePersonDetails(self.peopleList[0]);
      
        // Watch for the event on the shadow DOM
        self.shadowRoot.addEventListener('PersonClicked', (e) => {
          // e contains the id of person that was clicked.
          // We'll find him using this id in the self.people list:
          self.peopleList.forEach(person => {
            if (person.id == e.detail.personId) {
              // Update the personDetail component to reflect the click
              personDetail.updatePersonDetails(person);
            }
          })
        })
      }
  
    customElements.define('people-controller', PeopleController);
  })();