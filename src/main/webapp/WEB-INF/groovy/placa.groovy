import groovy.json.*
import java.text.SimpleDateFormat

// Locale para pegar o horario de brasilia
Locale braziliaLocale = new Locale("pt","BR","BRT")
def sdfDate = new SimpleDateFormat("dd/MM/yyyy")
sdfDate.setTimeZone(TimeZone.getTimeZone("GMT-3"));

def sdfTime = new SimpleDateFormat("kk:mm:ss")
sdfTime.setTimeZone(TimeZone.getTimeZone("GMT-3"));

def date = new Date()
def data = sdfDate.format(date)
def hora = sdfTime.format(date)

def placas = [:]
placas.put("AAA1111",[marca:"FIAT",modelo:"UNO MILLE FIRE 2P",cor:"AZUL",situacao:[codigo:"00", descricao:"SEM OCORRENCIAS"]])
placas.put("AAA2222",[marca:"VW",modelo:"GOL BOLA 4P",cor:"BRANCO",situacao:[codigo:"01", descricao:"ROUBADO"]])
placas.put("AAA3333",[marca:"GM",modelo:"CELTA LIFE 4P",cor:"PRATA",situacao:[codigo:"02", descricao:"FURTADO"]])
placas.put("AAA4444",[marca:"FORD",modelo:"FIESTA HATCH 4P",cor:"PRETO",situacao:[codigo:"03", descricao:"PLACA CLONADA"]])

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

