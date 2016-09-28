/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

/**
 *
 * @author Henike
 */
public class Values {

    private String sensor;
    private String data_hora;
    private float temperatura;
    private float luminosidade;
    private float umidade_ar;
    private float umidade_solo;

    /**
     * @return the sensor
     */
    public String getSensor() {
        return sensor;
    }

    /**
     * @param sensor the sensor to set
     */
    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    /**
     * @return the data_hora
     */
    public String getData_hora() {
        return data_hora;
    }

    /**
     * @param data_hora the data_hora to set
     */
    public void setData_hora(String data_hora) {
        this.data_hora = data_hora;
    }

    /**
     * @return the temperatura
     */
    public float getTemperatura() {
        return temperatura;
    }

    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * @return the luminosidade
     */
    public float getLuminosidade() {
        return luminosidade;
    }

    /**
     * @param luminosidade the luminosidade to set
     */
    public void setLuminosidade(float luminosidade) {
        this.luminosidade = luminosidade;
    }

    /**
     * @return the umidade_ar
     */
    public float getUmidade_ar() {
        return umidade_ar;
    }

    /**
     * @param umidade_ar the umidade_ar to set
     */
    public void setUmidade_ar(float umidade_ar) {
        this.umidade_ar = umidade_ar;
    }

    /**
     * @return the umidade_solo
     */
    public float getUmidade_solo() {
        return umidade_solo;
    }

    /**
     * @param umidade_solo the umidade_solo to set
     */
    public void setUmidade_solo(float umidade_solo) {
        this.umidade_solo = umidade_solo;
    }

}
