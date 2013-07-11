import groovy.json.*
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Locale.Builder

// Locale para pegar o horario de brasilia
Locale braziliaLocale = new Locale("pt","BR","BRT")

def date = new Date()
def data = new SimpleDateFormat("dd/MM/yyyy", braziliaLocale).format(date)
def hora = new SimpleDateFormat("kk:mm:ss", braziliaLocale).format(date)

def placas = [:]
placas.put("AAA1111",[marca:"FIAT",modelo:"UNO MILLE FIRE 2P",cor:"AZUL",roubado:"S"])
placas.put("AAA2222",[marca:"VW",modelo:"GOL BOLA 4P",cor:"BRANCO",roubado:"N"])

def json

def placa = placas[params.placa]
if (placa != null) {
  json = new JsonBuilder([codRet:"00", msgRet: "OK", placa: params.placa, veiculo: placa,  data:data, hora:hora])
} else {
  json = new JsonBuilder([codRet:"99", msgRet: "NENHUM REGISTRO ENCONTRADO PARA OS PARAMETROS INFORMADOS",  placa: params.placa, data:data, hora:hora] )
}
    
if (params.callback != null)
  println "${params.callback == '?' ? 'jsonp' : params.callback}(" + JsonOutput.prettyPrint(json.toString()) + ")"
else
  println JsonOutput.prettyPrint(json.toString())

