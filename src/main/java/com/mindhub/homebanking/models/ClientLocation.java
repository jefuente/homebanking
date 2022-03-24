package com.mindhub.homebanking.models;

import javax.persistence.*;

@Entity
public class ClientLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String country;

    private boolean enabled;

    @ManyToOne(targetEntity = Client.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "Client_id")
    private Client client;

    public ClientLocation() {
        super();
        enabled = false;
    }

    public ClientLocation(String country, Client client) {
        super();
        this.country = country;
        this.client = client;
        enabled = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((getCountry() == null) ? 0 : getCountry().hashCode());
        result = (prime * result) + (isEnabled() ? 1231 : 1237);
        result = (prime * result) + ((getId() == null) ? 0 : getId().hashCode());
        result = (prime * result) + ((getClient() == null) ? 0 : getClient().hashCode());
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
        final ClientLocation other = (ClientLocation) obj;
        if (getCountry() == null) {
            if (other.getCountry() != null) {
                return false;
            }
        } else if (!getCountry().equals(other.getCountry())) {
            return false;
        }
        if (isEnabled() != other.isEnabled()) {
            return false;
        }
        if (getId() == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (!getId().equals(other.getId())) {
            return false;
        }
        if (getClient() == null) {
            if (other.getClient() != null) {
                return false;
            }
        } else if (!getClient().equals(other.getClient())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserLocation [id=" + id + ", country=" + country + ", enabled=" + enabled + ", Client=" + client + "]";
    }

}
