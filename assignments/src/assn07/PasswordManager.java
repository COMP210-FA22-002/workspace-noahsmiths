package assn07;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password123";
    private Account[] _passwords;
    private int _size;
    private HashSet<K> _set;

    public PasswordManager() {
        _passwords = new Account[50];
        this._size = 0;
        this._set = new HashSet<>();
    }


    // TODO: put
    @Override
    public void put(K key, V value) {
        int index = Math.abs(key.hashCode()) % this._passwords.length;

        Account<K, V> account = new Account<K, V>(key, value);

        if (this._passwords[index] != null) {
            Account<K, V> previousAccount = this._passwords[index];
            account.setNext(previousAccount);

            while (previousAccount != null) {
                if (previousAccount.getWebsite().equals(key)) {
                    previousAccount.setPassword(value);
                    return;
                }

                previousAccount = previousAccount.getNext();
            }
        }

        this._set.add(key);
        this._passwords[index] = account;
        this._size++;
    }

    // TODO: get
    @Override
    public V get(K key) {
        int index = Math.abs(key.hashCode()) % this._passwords.length;
        Account<K, V> previousAccount = this._passwords[index];

        while (previousAccount != null) {
            if (previousAccount.getWebsite().equals(key)) {
                return previousAccount.getPassword();
            }

            previousAccount = previousAccount.getNext();
        }

        return null;
    }

    // TODO: size
    @Override
    public int size() {
        return this._size;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        return this._set;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int index = Math.abs(key.hashCode()) % this._passwords.length;

        Account<K, V> previousAccount = null;
        Account<K, V> account = this._passwords[index];
        
        while (account != null) {
            if (account.getWebsite().equals(key)) {
                V password = account.getPassword();

                if (previousAccount != null) {
                    previousAccount.setNext(account.getNext()); // MIGHT THROW NULL ERROR
                } else {
                    this._passwords[index] = null;
                }

                this._set.remove(key);
                this._size--;
                return password;
            }

            previousAccount = account;
            account = account.getNext();
        }
        
        return null;
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> duplicateList = new ArrayList<>();

        for (int i = 0; i < this._passwords.length; i++) {
            Account<K, V> account = this._passwords[i];

            while (account != null) {
                if (account.getPassword().equals(value)) {
                    duplicateList.add(account.getWebsite());
                }

                account = account.getNext();
            }
        }

        return duplicateList;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return enteredPassword.equals(MASTER_PASSWORD);
    }

    /*
    Generates random password of input length
     */
    @Override
    public String generateRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
