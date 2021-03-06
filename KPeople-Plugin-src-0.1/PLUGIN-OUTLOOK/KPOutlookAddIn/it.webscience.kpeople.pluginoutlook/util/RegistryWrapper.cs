﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Microsoft.Win32;
using System.Collections;
using System.Windows.Forms;
using System.Collections.Generic;

namespace KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.util
{
    class RegistryWrapper
    {
        #region Variables
        /// <summary>
        /// property value to hold the key to look for
        /// </summary>
        private string _key;

        /// <summary>
        /// property variable to hold the name of the key
        /// </summary>
        private string _keyName;

        /// <summary>
        /// property variable to hold the main
        /// registry key to start with
        /// </summary>
        private RegistryKey _mainKey;

        /// <summary>
        /// property variable to hold the success value
        /// of any of the processes in the wrapper class
        /// </summary>
        private bool _success;
        #endregion

        #region Properties
        /// <summary>
        /// property to hold the key we are looking for
        /// </summary>
        public string Key
        {
            get { return _key; }
            set { _key = value; }
        }

        /// <summary>
        /// property to hold the name of the key we are looking for/deleting
        /// </summary>
        public string KeyName
        {
            get { return _keyName; }
            set { _keyName = value; }
        }

        /// <summary>
        /// property to hold the main registry key
        /// we are starting our search in
        /// </summary>
        public RegistryKey MainKey
        {
            get { return _mainKey; }
            set { _mainKey = value; }
        }

        /// <summary>
        /// property to hold the success value (failed/succeeded)
        /// of any of the processes on the wrapper class
        /// </summary>
        public bool IsSuccessful
        {
            get { return _success; }
        }
        #endregion

        #region Class Methods
        #region CreateRegistrySubKey
        /// <summary>
        /// Function to create a new SubKey in the Windows Registry
        /// </summary>
        /// <param name="KeyPermissions">RegistryKepPermissionCheck -> Specifies permissions of the SubKey to be created</param>
        /// <returns>True (Succeeded)/False (Failed)</returns>
        /// <remarks>Created 22DEC07 -> Richard L. McCutchen</remarks>
        public bool CreateRegistrySubKey(RegistryKeyPermissionCheck KeyPermissions)
        {
            try
            {
                //use the CreateSubKey Method for creating our
                //new registry SubKey
                _mainKey.CreateSubKey(_key, KeyPermissions);
                //set out success variable to tru since it appears to
                //have gone well, the catch block will catch any Exceptions
                //that may occur
                _success = true;
            }
            catch (Exception ex)
            {
                //since an exception occurred we need to let the user know
                MessageBox.Show(ex.Message, "Error: Creating SubKey", MessageBoxButtons.OK, MessageBoxIcon.Error);
                //set our success variable to false since it failed
                _success = false;
            }
            //return the value to the calling method
            return _success;
        }
        #endregion

        #region WriteSubKeyValue
        /// <summary>
        /// Writes a value in the Registry
        /// </summary>
        /// <param name="_mainKey">RegistryKey -> One of the 6 main keys that you want to write to</param>
        /// <param name="_key">String -> Name of the subkey you want to write to. If the subkey doesnt
        /// exist it will be created</param>
        /// <param name="_keyName">String -> Name of the value to create</param>
        /// <param name="oNameValue">Object -> Value to be stored</param>
        /// <param name="RegType">RegistryValueKind -> Data type of the subkey value</param>
        /// <returns>True (Succeedeed)/False (Failed)</returns>
        /// <remarks>Created 22DEC07 -> Richard L. McCutchen</remarks>
        public bool WriteSubKeyValue(object oNameValue, RegistryValueKind RegType)
        {
            RegistryKey rkKey;
            try
            {
                //Open the given subkey
                rkKey = _mainKey.OpenSubKey(_key, true);
                //check to see if the subkey exists
                if (rkKey == null)
                {
                    //doesnt exist
                    //create the subkey
                    rkKey = _mainKey.CreateSubKey(_key, RegistryKeyPermissionCheck.Default);
                }
                //set the value of the subkey
                rkKey.SetValue(_keyName, oNameValue, RegType);
                _success = true;
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Error: Writing Registry Value", MessageBoxButtons.OK, MessageBoxIcon.Error);
                _success = false;
            }
            return _success;
        }
        #endregion

        #region ReadRegistryValue
        /// <summary>
        /// Function to read a value from the Registry
        /// </summary>
        /// <param name="oNameValue">Object -> The value to be read</param>
        /// <returns>True (Succeeded)/False (Failed)</returns>
        /// <remarks>Created 22DEC07 -> Richard L. McCutchen</remarks>
        public string ReadRegistryValue(ref object nameValue)
        {
            //create a RegistryKey instance
            RegistryKey rkKey;
            //create a string variable to hold the value
            //of the sub key we're reading, we then initialize
            //it to an empty string to prevent a NullReferenceException
            //this variable will be used for returning the value of the
            //subkey we're reading
            string keyValue = string.Empty;
            try
            {
                //open the given subkey
                rkKey = _mainKey.OpenSubKey(_key, true);
                //check to see if the subkey exists
                if (rkKey == null)
                {
                    //it doesnt exist
                    //throw an exception
                    throw new Exception("The Registry SubKey provided doesnt exist!");
                }
                //get the value
                nameValue = rkKey.GetValue(_keyName);
                //set the value of our return value, but
                //we need the ToString value since our
                //variable is a string type
                keyValue = nameValue.ToString();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Error: Reading Registry Value", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            //return the value to the calling method
            return keyValue;
        }
        #endregion

        #region DeleteSubKeyValue
        /// <summary>
        /// Function to delete a subkey value from the Windows Registry
        /// </summary>
        /// <param name="_mainKey">RegistryKey -> One of the 6 main keys you want to delete from</param>
        /// <param name="_key">String -> Name of the SubKey you want to delete a value from</param>
        /// <param name="_keyName">String -> Name of the value to delete</param>
        /// <returns>True (Succeeded)/False (Failed)</returns>
        /// <remarks>Created 22DEC07 -> Richard L. McCutchen</remarks>
        public bool DeleteSubKeyValue()
        {
            RegistryKey rkKey;
            try
            {
                //open the given subkey
                rkKey = _mainKey.OpenSubKey(_key, true);
                //check to make sure the subkey exists
                if ((_key != null))
                {
                    //subkey exists
                    //delete the subkey
                    _mainKey.DeleteValue(_keyName, true);
                    _success = true;
                }
                else
                {
                    _success = false;
                    //subkey doesnt exist
                    //throw an exception
                    throw new Exception("The SubKey provided doesnt exist! Please check your entry and try again");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Error: Deleting SubKey Value", MessageBoxButtons.OK, MessageBoxIcon.Error);
                _success = false;
            }
            return _success;
        }
        #endregion

        #region DeleteRegistrySubKey
        /// <summary>
        /// Function to delete a SubKey from the Windows Registry
        /// </summary>
        /// <returns>True (Succeeded)/False (Failed)</returns>
        /// <remarks>Created 22DEC07 -> Richard L. McCutchen</remarks>
        public bool DeleteRegistrySubKey()
        {
            RegistryKey rkKey;
            try
            {
                //open the given subkey
                rkKey = _mainKey.OpenSubKey(_key, true);
                //check to make sure the subkey exists
                if ((_key != null))
                {
                    //subkey exists
                    MainKey.DeleteSubKey(_key, true);
                    _success = true;
                }
                else
                {
                     _success = false;
                    //subkey doesnt exist
                    //throw an exception letting the user know
                    throw new Exception("The SubKey provided doesn't exist. Please check your entry and try again.");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Error: Deleting SubKey", MessageBoxButtons.OK, MessageBoxIcon.Error);
                _success = false;
            }
            return _success;
        }
        #endregion

        #region GetAllChildSubKeys
        /// <summary>
        /// Function to retrieve all the child subkeys of a SubKey in the Windows Registry
        /// </summary>
        /// <returns>An ArrayList of all the child subkeys</returns>
        /// <remarks>Created 22DEC07 -> Richard L. McCutchen</remarks>
        public List<string> GetAllChildSubKeys()
        {
            RegistryKey rkKey;
            //RegistryKey to work with
            string[] sSubKeys;
            //string array to hold the subkeys
            List<string> arySubKeys = new List<string>();
            //arraylist to return the subkeys in an array
                //open the given subkey
                rkKey = _mainKey.OpenSubKey(_key);
                //check to see if the subkey exists
                if (!(_key == null))
                {
                    //subkey exists
                    //get all the child subkeys
                    sSubKeys = rkKey.GetSubKeyNames();
                    //loop through all the child subkeys
                    foreach (string sub in sSubKeys)
                    {
                        //add them to the arraylist
                        arySubKeys.Add(sub);
                    }
                }
                else
                {
                    //subkey doesnt exist
                    //throw an exception
                    throw new Exception("The SubKey provided doesn't exist. Please check your entry and try again.");
                }
            //return the subkeys arraylist
            return arySubKeys;
        }

        #endregion
        #endregion
    }
}

