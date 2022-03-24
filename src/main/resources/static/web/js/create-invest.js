var app = new Vue({
    el:"#app",
    data:{
        investTypes: [],
        investTypeId: 0,
        plazos: 0,
        plazosList: [],
        clientAccounts: [],
        errorToats: null,
        errorMsg: null,
        accountFromNumber: "VIN",
        amount: 0,
        //fees: []
    },
    methods:{
        getData: function(){
            Promise.all([axios.get("/api/invests"),axios.get("/api/clients/current/accounts")])
                .then((response) => {
                    //get loan types ifo
                    this.investTypes = response[0].data;
                    this.clientAccounts = response[1].data;
                })
                .catch((error) => {
                    this.errorMsg = "Error getting data";
                    this.errorToats.show();
                })
        },
        formatDate: function(date){
            return new Date(date).toLocaleDateString('en-gb');
        },
        checkApplication: function(){
            if(this.investTypeId == 0){
                this.errorMsg = "You must select a invest type";
                this.errorToats.show();
            }
            else if(this.plazos == 0){
                this.errorMsg = "You must select plazo";
                this.errorToats.show();
            }
            else if(this.accountFromNumber == "VIN"){
                this.errorMsg = "You must indicate an account";
                this.errorToats.show();
            }
            else if(this.amount == 0){
                this.errorMsg = "You must indicate an amount";
                this.errorToats.show();
            }else{
                this.modal.show();
            }
        },
        apply: function(){
            axios.post("/api/invests",{investId: this.investTypeId, amount: this.amount, plazos: this.plazos, fromAccountNumber: this.accountFromNumber})
                .then(response => {
                    this.modal.hide();
                    this.okmodal.show();
                })
                .catch((error) =>{
                    this.errorMsg = error.response.data;
                    this.errorToats.show();
                })
        },
        changedType: function(){
            this.plazosList = this.investTypes.find(investType => investType.id == this.investTypeId).plazos;
        },
        finish: function(){
            window.location.reload();
        },
        /*
        checkFees: function(){
            this.fees = [];
            this.totalLoan = parseInt(this.amount) + (this.amount * 0.2);
            let amount = this.totalLoan / this.payments;
            for(let i = 1; i <= this.payments; i++){
                this.fees.push({ amount: amount });
            }
            this.feesmodal.show();
        },
        */
        signOut: function(){
            axios.post('/api/logout')
                .then(response => window.location.href="/index.html")
                .catch(() =>{
                    this.errorMsg = "Sign out failed"
                    this.errorToats.show();
                })
        },
    },
    mounted: function(){
        this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));
        this.modal = new bootstrap.Modal(document.getElementById('confirModal'));
        this.okmodal = new bootstrap.Modal(document.getElementById('okModal'));
        this.feesmodal = new bootstrap.Modal(document.getElementById('feesModal'));
        this.getData();
    }
})