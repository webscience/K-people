﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Il codice è stato generato da uno strumento.
//     Versione runtime:4.0.30319.1
//
//     Le modifiche apportate a questo file possono provocare un comportamento non corretto e andranno perse se
//     il codice viene rigenerato.
// </auto-generated>
//------------------------------------------------------------------------------

namespace KPOutlookAddIn.ProcessMetadataService {
    
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.0.30319.1")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="http://process.service.kpeople.webscience.it")]
    public partial class KPeopleServiceException : object, System.ComponentModel.INotifyPropertyChanged {
        
        private KPeopleServiceException1 kPeopleServiceException1Field;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute("KPeopleServiceException", IsNullable=true, Order=0)]
        public KPeopleServiceException1 KPeopleServiceException1 {
            get {
                return this.kPeopleServiceException1Field;
            }
            set {
                this.kPeopleServiceException1Field = value;
                this.RaisePropertyChanged("KPeopleServiceException1");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.0.30319.1")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(TypeName="KPeopleServiceException", Namespace="http://exception.service.kpeople.webscience.it/xsd")]
    public partial class KPeopleServiceException1 : object, System.ComponentModel.INotifyPropertyChanged {
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.0.30319.1")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://datatypes.service.kpeople.webscience.it/xsd")]
    public partial class ProcessRule : object, System.ComponentModel.INotifyPropertyChanged {
        
        private string keyField;
        
        private string valueField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true, Order=0)]
        public string key {
            get {
                return this.keyField;
            }
            set {
                this.keyField = value;
                this.RaisePropertyChanged("key");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true, Order=1)]
        public string value {
            get {
                return this.valueField;
            }
            set {
                this.valueField = value;
                this.RaisePropertyChanged("value");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.0.30319.1")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://datatypes.service.kpeople.webscience.it/xsd")]
    public partial class ProcessExtraData : object, System.ComponentModel.INotifyPropertyChanged {
        
        private string keyField;
        
        private string valueField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true, Order=0)]
        public string key {
            get {
                return this.keyField;
            }
            set {
                this.keyField = value;
                this.RaisePropertyChanged("key");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true, Order=1)]
        public string value {
            get {
                return this.valueField;
            }
            set {
                this.valueField = value;
                this.RaisePropertyChanged("value");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.0.30319.1")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://datatypes.service.kpeople.webscience.it/xsd")]
    public partial class ProcessValue : object, System.ComponentModel.INotifyPropertyChanged {
        
        private string descriptionField;
        
        private ProcessExtraData[] listProcessExtraDataField;
        
        private string valueField;
        
        private int zidStateField;
        
        private bool zidStateFieldSpecified;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true, Order=0)]
        public string description {
            get {
                return this.descriptionField;
            }
            set {
                this.descriptionField = value;
                this.RaisePropertyChanged("description");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute("listProcessExtraData", IsNullable=true, Order=1)]
        public ProcessExtraData[] listProcessExtraData {
            get {
                return this.listProcessExtraDataField;
            }
            set {
                this.listProcessExtraDataField = value;
                this.RaisePropertyChanged("listProcessExtraData");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true, Order=2)]
        public string value {
            get {
                return this.valueField;
            }
            set {
                this.valueField = value;
                this.RaisePropertyChanged("value");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Order=3)]
        public int zidState {
            get {
                return this.zidStateField;
            }
            set {
                this.zidStateField = value;
                this.RaisePropertyChanged("zidState");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool zidStateSpecified {
            get {
                return this.zidStateFieldSpecified;
            }
            set {
                this.zidStateFieldSpecified = value;
                this.RaisePropertyChanged("zidStateSpecified");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.0.30319.1")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://datatypes.service.kpeople.webscience.it/xsd")]
    public partial class ProcessMetadata : object, System.ComponentModel.INotifyPropertyChanged {
        
        private string descriptionField;
        
        private string keyField;
        
        private ProcessValue[] valueListField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true, Order=0)]
        public string description {
            get {
                return this.descriptionField;
            }
            set {
                this.descriptionField = value;
                this.RaisePropertyChanged("description");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true, Order=1)]
        public string key {
            get {
                return this.keyField;
            }
            set {
                this.keyField = value;
                this.RaisePropertyChanged("key");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute("valueList", IsNullable=true, Order=2)]
        public ProcessValue[] valueList {
            get {
                return this.valueListField;
            }
            set {
                this.valueListField = value;
                this.RaisePropertyChanged("valueList");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.0.30319.1")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://datatypes.service.kpeople.webscience.it/xsd")]
    public partial class ProcessCompoundMetadata : object, System.ComponentModel.INotifyPropertyChanged {
        
        private string descriptionField;
        
        private string keyField;
        
        private ProcessMetadata[] metadataListField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true, Order=0)]
        public string description {
            get {
                return this.descriptionField;
            }
            set {
                this.descriptionField = value;
                this.RaisePropertyChanged("description");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true, Order=1)]
        public string key {
            get {
                return this.keyField;
            }
            set {
                this.keyField = value;
                this.RaisePropertyChanged("key");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute("metadataList", IsNullable=true, Order=2)]
        public ProcessMetadata[] metadataList {
            get {
                return this.metadataListField;
            }
            set {
                this.metadataListField = value;
                this.RaisePropertyChanged("metadataList");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.0.30319.1")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://datatypes.service.kpeople.webscience.it/xsd")]
    public partial class ProcessMetadataSet : object, System.ComponentModel.INotifyPropertyChanged {
        
        private ProcessCompoundMetadata[] cmpMetadataListField;
        
        private ProcessRule[] ruleSetListField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute("cmpMetadataList", IsNullable=true, Order=0)]
        public ProcessCompoundMetadata[] cmpMetadataList {
            get {
                return this.cmpMetadataListField;
            }
            set {
                this.cmpMetadataListField = value;
                this.RaisePropertyChanged("cmpMetadataList");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute("ruleSetList", IsNullable=true, Order=1)]
        public ProcessRule[] ruleSetList {
            get {
                return this.ruleSetListField;
            }
            set {
                this.ruleSetListField = value;
                this.RaisePropertyChanged("ruleSetList");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.0.30319.1")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://datatypes.service.kpeople.webscience.it/xsd")]
    public partial class User : object, System.ComponentModel.INotifyPropertyChanged {
        
        private string accountField;
        
        private string emailField;
        
        private string firstNameField;
        
        private string hpmUserIdField;
        
        private int idUserField;
        
        private bool idUserFieldSpecified;
        
        private string lastNameField;
        
        private string screenNameField;
        
        private string usernameField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true, Order=0)]
        public string account {
            get {
                return this.accountField;
            }
            set {
                this.accountField = value;
                this.RaisePropertyChanged("account");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true, Order=1)]
        public string email {
            get {
                return this.emailField;
            }
            set {
                this.emailField = value;
                this.RaisePropertyChanged("email");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true, Order=2)]
        public string firstName {
            get {
                return this.firstNameField;
            }
            set {
                this.firstNameField = value;
                this.RaisePropertyChanged("firstName");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true, Order=3)]
        public string hpmUserId {
            get {
                return this.hpmUserIdField;
            }
            set {
                this.hpmUserIdField = value;
                this.RaisePropertyChanged("hpmUserId");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Order=4)]
        public int idUser {
            get {
                return this.idUserField;
            }
            set {
                this.idUserField = value;
                this.RaisePropertyChanged("idUser");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool idUserSpecified {
            get {
                return this.idUserFieldSpecified;
            }
            set {
                this.idUserFieldSpecified = value;
                this.RaisePropertyChanged("idUserSpecified");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true, Order=5)]
        public string lastName {
            get {
                return this.lastNameField;
            }
            set {
                this.lastNameField = value;
                this.RaisePropertyChanged("lastName");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true, Order=6)]
        public string screenName {
            get {
                return this.screenNameField;
            }
            set {
                this.screenNameField = value;
                this.RaisePropertyChanged("screenName");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true, Order=7)]
        public string username {
            get {
                return this.usernameField;
            }
            set {
                this.usernameField = value;
                this.RaisePropertyChanged("username");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://process.service.kpeople.webscience.it", ConfigurationName="ProcessMetadataService.ProcessMetadataServicePortType")]
    public interface ProcessMetadataServicePortType {
        
        // CODEGEN: Il parametro 'return' richiede informazioni sullo schema aggiuntive che non possono essere acquisite utilizzando la modalità parametro. L'attributo specifico è 'System.Xml.Serialization.XmlElementAttribute'.
        [System.ServiceModel.OperationContractAttribute(Action="urn:getMetadataProcessV1", ReplyAction="urn:getMetadataProcessV1Response")]
        [System.ServiceModel.FaultContractAttribute(typeof(KPOutlookAddIn.ProcessMetadataService.KPeopleServiceException), Action="urn:getMetadataProcessV1KPeopleServiceException", Name="KPeopleServiceException")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
        KPOutlookAddIn.ProcessMetadataService.getMetadataProcessV1Response getMetadataProcessV1(KPOutlookAddIn.ProcessMetadataService.getMetadataProcessV1Request request);
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="getMetadataProcessV1", WrapperNamespace="http://process.service.kpeople.webscience.it", IsWrapped=true)]
    public partial class getMetadataProcessV1Request {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://process.service.kpeople.webscience.it", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true)]
        public KPOutlookAddIn.ProcessMetadataService.User user;
        
        public getMetadataProcessV1Request() {
        }
        
        public getMetadataProcessV1Request(KPOutlookAddIn.ProcessMetadataService.User user) {
            this.user = user;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="getMetadataProcessV1Response", WrapperNamespace="http://process.service.kpeople.webscience.it", IsWrapped=true)]
    public partial class getMetadataProcessV1Response {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://process.service.kpeople.webscience.it", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true)]
        public KPOutlookAddIn.ProcessMetadataService.ProcessMetadataSet @return;
        
        public getMetadataProcessV1Response() {
        }
        
        public getMetadataProcessV1Response(KPOutlookAddIn.ProcessMetadataService.ProcessMetadataSet @return) {
            this.@return = @return;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface ProcessMetadataServicePortTypeChannel : KPOutlookAddIn.ProcessMetadataService.ProcessMetadataServicePortType, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class ProcessMetadataServicePortTypeClient : System.ServiceModel.ClientBase<KPOutlookAddIn.ProcessMetadataService.ProcessMetadataServicePortType>, KPOutlookAddIn.ProcessMetadataService.ProcessMetadataServicePortType {
        
        public ProcessMetadataServicePortTypeClient() {
        }
        
        public ProcessMetadataServicePortTypeClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public ProcessMetadataServicePortTypeClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public ProcessMetadataServicePortTypeClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public ProcessMetadataServicePortTypeClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        KPOutlookAddIn.ProcessMetadataService.getMetadataProcessV1Response KPOutlookAddIn.ProcessMetadataService.ProcessMetadataServicePortType.getMetadataProcessV1(KPOutlookAddIn.ProcessMetadataService.getMetadataProcessV1Request request) {
            return base.Channel.getMetadataProcessV1(request);
        }
        
        public KPOutlookAddIn.ProcessMetadataService.ProcessMetadataSet getMetadataProcessV1(KPOutlookAddIn.ProcessMetadataService.User user) {
            KPOutlookAddIn.ProcessMetadataService.getMetadataProcessV1Request inValue = new KPOutlookAddIn.ProcessMetadataService.getMetadataProcessV1Request();
            inValue.user = user;
            KPOutlookAddIn.ProcessMetadataService.getMetadataProcessV1Response retVal = ((KPOutlookAddIn.ProcessMetadataService.ProcessMetadataServicePortType)(this)).getMetadataProcessV1(inValue);
            return retVal.@return;
        }
    }
}
