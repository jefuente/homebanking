package com.mindhub.homebanking.models;

import javax.persistence.*;

@Entity
public class NewLocationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = ClientLocation.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_location_id")
    private ClientLocation clientLocation;

    //

    public NewLocationToken() {
        super();
    }

    public NewLocationToken(final String token) {
        super();
        this.token = token;
    }

    public NewLocationToken(final String token, final ClientLocation clientLocation) {
        super();
        this.token = token;
        this.clientLocation = clientLocation;
    }

    //

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ClientLocation getClientLocation() {
        return clientLocation;
    }

    public void setClientLocation(ClientLocation clientLocation) {
        this.clientLocation = clientLocation;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((getId() == null) ? 0 : getId().hashCode());
        result = (prime * result) + ((getToken() == null) ? 0 : getToken().hashCode());
        result = (prime * result) + ((getClientLocation() == null) ? 0 : getClientLocation().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NewLocationToken other = (NewLocationToken) obj;
        if (getId() == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (!getId().equals(other.getId())) {
            return false;
        }
        if (getToken() == null) {
            if (other.getToken() != null) {
                return false;
            }
        } else if (!getToken().equals(other.getToken())) {
            return false;
        }
        if (getClientLocation() == null) {
            if (other.getClientLocation() != null) {
                return false;
            }
        } else if (!getClientLocation().equals(other.getClientLocation())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NewLocationToken [id=" + id + ", token=" + token + ", clientLocation=" + clientLocation + "]";
    }

}